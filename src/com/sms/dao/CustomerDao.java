package com.sms.dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sms.model.Customer;
import com.sms.util.StringUtil;


public class CustomerDao {
	
	
	
	
	public Customer searchByID(Connection con,String id) throws SQLException {
		
		Customer customer = new Customer();
		ByteArrayOutputStream baos = null;
		ByteArrayOutputStream bos = null;
		Icon IC = new ImageIcon();
		
		StringBuffer sb=new StringBuffer("select * from CUSTOMER");
		if(StringUtil.isNotEmpty(id)){
			sb.append(" and cus_id ="+id);
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			
			InputStream is = rs.getBinaryStream("cus_picture");
			baos = new ByteArrayOutputStream();
			int b = 0;
			try {
				while ((b = is.read()) != -1) {
					baos.write(b);
				}
				bos=PhotoDao.changeSize(160, 160, baos);
				IC = new ImageIcon(bos.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			String cus_address = null;
			int cus_id = -1 ;
			int cus_order_num  = -1 ;
			ImageIcon cus_picture = null;
			String cus_pwd = null;
			Date cus_register_time = null;
			Long cus_telephone = null;
			String cus_username = null;
			int cus_source = -1;

			
			
			cus_address=rs.getString("cus_address");
			cus_id=rs.getInt("cus_id");
			cus_order_num=rs.getInt("cus_order_num");
			cus_picture=(ImageIcon)IC;
			cus_pwd=rs.getString("cus_pwd");
			cus_register_time = rs.getDate("cus_register_time");
			cus_telephone = rs .getLong("cus_telephone");
			cus_username = rs.getString("cus_username");
			cus_source = rs.getInt("cus_source");
			
			
			customer.setCus_address(cus_address);
			customer.setCus_id(cus_id);
			customer.setCus_order_num(cus_order_num);
			customer.setCus_picture(cus_picture);
			customer.setCus_pwd(cus_pwd);
			customer.setCus_register_time(cus_register_time);
			customer.setcus_telephone(cus_telephone);
			customer.setCus_username(cus_username);
			customer.setCus_source(cus_source);
		}
		
		return customer;
	}
	
	
	
	
public int checkPower(Connection con,String id) throws SQLException {
		
		int power=0;
	
		StringBuffer sb=new StringBuffer("select count(*) from CUSTOMER where cus_source = "+id);
		
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			power = rs.getInt(1);
			
		}
		
		return power;
	}
	
	

	public  int add(Connection con,Customer customer) throws Exception{
		
/*		 select "cus_id" from customer;
 * insert into "CUSTOMER" values(null,'我懂你','123',0,1,to_date('2001/06/07 14:20:23','YYYY/MM/DD HH24:MI:SS'),null,17225674321,'四川达州');

		private int cus_id;
		private String cus_username;
		private String cus_pwd;
		private int cus_order_num;
		private int cus_source;
		private Date cus_register_time;
		private ImageIcon cus_picture ;
		private long cus_telephone;
		private String cus_adddress;
		*/
		
		ImageIcon ima = customer.getCus_picture();
		BufferedImage bu = new BufferedImage(ima.getImage().getWidth(null), ima
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		ImageIO.write(bu, "png", imageStream);
		
	    FileOutputStream out = new FileOutputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_customer_add.png");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(imageStream.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_customer_add.png");
		
		
		String sql="insert into CUSTOMER values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,customer.getCus_username());
		pstmt.setString(2,customer.getCus_pwd());
		pstmt.setInt(3,customer.getCus_order_num());
		pstmt.setInt(4,customer.getCus_source());
		pstmt.setDate(5, customer.getCus_register_time());
		pstmt.setBinaryStream(6,str,str.available());
		pstmt.setLong(7, customer.getcus_telephone());
		pstmt.setString(8,customer.getCus_address());
		
		return pstmt.executeUpdate();
	}
	/**
	 * 返回结果集
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception 
	 */
	public ResultSet list(Connection con,Customer customer) throws Exception{
		
		/*		

		private int cus_id;
		private String cus_username;
		private String cus_pwd;
		private int cus_order_num;
		private int cus_source;
		private Date cus_register_time;
		private ImageIcon cus_picture ;
		private long cus_telephone;
		private String cus_adddress;
		*/
		
		
		StringBuffer sb=new StringBuffer("select * from CUSTOMER");
		if(StringUtil.isNotEmpty(customer.getCus_username())){

			System.out.println(customer.getCus_username());
			sb.append(" and cus_username like '%"+customer.getCus_username()+"%'");
		}
		/*
		if(!((""+customer.getCus_id()).equals("0")   ||   customer.getCus_id()==0)){*/
		if(customer.getCus_id()!=0){
			sb.append(" and cus_id ="+customer.getCus_id());
			System.out.println("id栏不为空"+customer.getCus_id());
		}else {

			System.out.println("id栏为空");
		}
		sb.append(" order by cus_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	
	
	

public ResultSet listForPower(Connection con,Customer customer) throws Exception{
	
	
	
	StringBuffer sb=new StringBuffer("select * from CUSTOMER where cus_source = "+customer.getCus_id());
	
	PreparedStatement pstmt=con.prepareStatement(sb.toString());
	return pstmt.executeQuery();
	
	
	
}


public ResultSet listForOrder(Connection con,Customer customer) throws Exception{
	
	
	
	StringBuffer sb=new StringBuffer("select * from \"ORDER\" where cus_id = "+customer.getCus_id());
	
	PreparedStatement pstmt=con.prepareStatement(sb.toString());
	return pstmt.executeQuery();
	
	
	
}


	
public ResultSet listForSource(Connection con,Customer customer) throws Exception{
		
		/*		

		private int cus_id;
		private String cus_username;
		private String cus_pwd;
		private int cus_order_num;
		private int cus_source;
		private Date cus_register_time;
		private ImageIcon cus_picture ;
		private long cus_telephone;
		private String cus_adddress;
		
		
		
		
		select id,username,source,times,csource from (
		(select t.cus_id id,t.cus_username username,t. cus_source csource from customer t) t1 
		left join (
		select t2.cus_source source,count(t2.cus_source) times
		 from customer t2 group by t2.cus_source
		) t3
		on t1. id=t3. source
		);

		
		
		
		
		
		*/
		
		
		StringBuffer sb=new StringBuffer("select id,username,source,times,csource from (" + 
				"		(select t.cus_id id,t.cus_username username,t. cus_source csource from customer t) t1 " + 
				"		left join (" + 
				"		select t2.cus_source source,count(t2.cus_source) times" + 
				"		 from customer t2 group by t2.cus_source" + 
				"		) t3" + 
				"		on t1. id=t3. source" + 
				"		)" + 
				"");
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	/**
	 * 删除宠物类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exceptrion
	 */
public int delete(Connection con,Customer customer)throws Exception{
		
		int id = customer.getCus_id();
		String sql="delete from CUSTOMER where cus_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}
	
public int delete(Connection con,String idstr)throws Exception{
		
		int id = Integer.parseInt(idstr);
		String sql="delete from CUSTOMER where cus_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}

public int delete(Connection con,int id)throws Exception{

	String sql="delete from CUSTOMER where cus_id=?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setInt(1,id);
	return pstmt.executeUpdate();
}
	/**
	 * 修改宠物类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exceptrion
	 */
	public int update(Connection con,Customer customer)throws Exception{
		//update CLASS set "class_name"='街舞服饰' ,"class_remark"='街舞，流行，嘻哈' where "class_id"=24;
		
		

		ImageIcon imageIcon = customer.getCus_picture();
		
		Image image = imageIcon.getImage();
		
		

		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		Graphics gc = bufferedImage.createGraphics();
        gc.drawImage(image,0,0,null);
		
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", bos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_customer_update.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(bos.toByteArray());    
	    out.close();  
	    
/*//	    
 * 
 * PhotoTest photoTest = new PhotoTest(new ImageIcon("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_customer_update.jpg"));
//		photoTest.setVisible(true);
//		
*/		
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_customer_update.jpg");

	    String sql="update CUSTOMER set "
				+ "cus_username=?"
//				+ ",cus_pwd=?"
				+ ",cus_order_num=?"
				+ ",cus_source=?"
//				+ ",cus_register_time=?"
				+ ",cus_picture=?"
//				+ ",cus_telephone=?"
//				+ ",cus_address=?"
				+ "  where cus_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,customer.getCus_username());
//		pstmt.setString(2,customer.getCus_pwd());
		pstmt.setInt(2,customer.getCus_order_num());
		pstmt.setInt(3,customer.getCus_source());
//		pstmt.setDate(5, customer.getCus_register_time());
		pstmt.setBinaryStream(4,str,str.available());
//		pstmt.setLong(7, customer.getcus_telephone());
//		pstmt.setString(8, customer.getCus_address());
		pstmt.setInt(5,customer.getCus_id());
		return pstmt.executeUpdate();
		
		
		
		
		/*String sql="update CUSTOMER set "
				+ ",cus_username=?"
				+ ",cus_pwd=?"
				+ ",cus_order_num=?"
				+ ",cus_source=?"
				+ ",cus_register_time=?"
				+ ",cus_picture=?"
				+ ",cus_telephone=?"
				+ ",cus_address=?"
				+ "  where cus_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,customer.getCus_username());
		pstmt.setString(2,customer.getCus_pwd());
		pstmt.setInt(3,customer.getCus_order_num());
		pstmt.setInt(4,customer.getCus_source());
		pstmt.setDate(5, customer.getCus_register_time());
		pstmt.setBinaryStream(6,str,str.available());
		pstmt.setLong(7, customer.getcus_telephone());
		pstmt.setString(8, customer.getCus_address());
		pstmt.setInt(9,customer.getCus_id());
		return pstmt.executeUpdate();*/
	}
}
