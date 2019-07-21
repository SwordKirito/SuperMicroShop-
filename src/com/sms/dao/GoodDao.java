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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sms.model.Good;
import com.sms.util.StringUtil;

public class GoodDao {
	
	ClassDao classDao = new ClassDao();

	
	public int getGoodIdByName(Connection con,String name) throws SQLException {
		
		int id=0;
		
		StringBuffer sb=new StringBuffer("select * from GOOD where good_name=?");
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		
		pstmt.setString(1, name);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			id = rs.getInt("good_id");
		}
		return id;
		
	}
	
	public String isHot(Connection con,Good good) throws SQLException {
		
		String sql = "select ishot(good_hot) from good where good_id = "+good.getGood_id();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		String str="";
		
		if(rs.next()) {
			str=rs.getString("ishot(good_hot)");
		}

		return str;
	}
	
	
	public int setHotOrCancel(Connection con,Good good) throws SQLException {
		
		String sql = "update GOOD SET good_hot = ? where good_id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);

		if(good.getGood_hot()==0) {
			pstmt.setInt(1, 1);
		}else {
			pstmt.setInt(1, 0);
		}
		pstmt.setInt(2, good.getGood_id());
		
		return pstmt.executeUpdate();
	}
	
public int setHotOrCancel(Connection con,String idstr) throws SQLException {
		
		String sql = "update GOOD SET good_hot = ? where good_id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		Good good = this.searchByID(con, idstr);

		if(good.getGood_hot()==0) {
			pstmt.setInt(1, 1);
		}else {
			pstmt.setInt(1, 0);
		}
		pstmt.setInt(2, Integer.parseInt(idstr));
		
		return pstmt.executeUpdate();
	}
	

	public ResultSet getItsClass(Connection con,Good good) throws SQLException {
		
		if (good.getGood_id()==0||"".equals(""+good.getGood_id())) {
			System.out.println("good 是空的");
			return null;
		}
			
			String sql="select class_name from GOOD natural join GOOD_CLASS natural join CLASS where good_id = "+good.getGood_id();
			
			PreparedStatement pstmt=con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
		/*	while(rs.next()) {
				System.out.println(rs.getString(1));
			}*/
			
			return rs;
	}
	
	
	public Good searchByID(Connection con,String id) throws SQLException {
		
		Good good = new Good();
		ByteArrayOutputStream baos = null;
		ByteArrayOutputStream bos = null;
		Icon IC = new ImageIcon();
		
		StringBuffer sb=new StringBuffer("select * from GOOD");
		if(StringUtil.isNotEmpty(id)){
			sb.append(" and good_id ="+id);
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			
			InputStream is = rs.getBinaryStream("good_picture");
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
			

			int good_id  = -1 ;
			String good_name = null;
			String good_descrip = null;
			int good_cost  = -1 ;
			int good_price  = -1 ;
			String good_place = null;
			int good_hot  = 0 ;
			
	
			good_id  = rs.getInt("good_id") ;
			good_name = rs.getString("good_name");
			good_descrip = rs.getString("good_descrip");
			good_cost  = rs.getInt("good_cost") ;
			good_price  = rs.getInt("good_price") ;
			good_hot  = rs.getInt("good_hot") ;
			good_place = rs.getString("good_place");
			
			
			good.setGood_id(good_id);
			good.setGood_name(good_name);
			good.setGood_descrip(good_descrip);
			good.setGood_cost(good_cost);
			good.setGood_price(good_price);
			good.setGood_hot(good_hot);
			good.setGood_place(good_place);
			good.setGood_picture((ImageIcon) IC);
		}
		
		return good;
	}
	
	
	
	
	
	
	public int add(Connection con, Good good) throws SQLException, IOException {
		// TODO Auto-generated method stub
		

		//图片存到文件中并获取文件输入流
		ImageIcon imageIcon = good.getGood_picture();
		Image image = imageIcon.getImage();
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
        gc.drawImage(image,0,0,null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", bos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_good_add.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(bos.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_good_add.jpg");

	    /*int good_id;
		String good_name;
		String good_descrip;
		int good_cost;
		int good_price;
		String good_place;
		ImageIcon good_picture;
		int good_hot;*/
		
	    //int good_id = good.getGood_id();
		String good_name = good.getGood_name();
		String good_descrip = good.getGood_descrip();
		int good_cost = good.getGood_cost();
		int good_price = good.getGood_price();
		String good_place = good.getGood_place();
		//ImageIcon good_picture = good.getGood_picture();
		int good_hot=good.getGood_hot();
	    
	    String sql="insert into GOOD values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,good_name);
		pstmt.setString(2,good_descrip);
		pstmt.setInt(3,good_cost);
		pstmt.setInt(4,good_price);
		pstmt.setString(5,good_place);
		pstmt.setBinaryStream(6,str,str.available());
		pstmt.setInt(7,good_hot);
		
		
		
		return pstmt.executeUpdate();
		
	}

	public int delete(Connection con,String idstr)throws Exception{
		
		int id = Integer.parseInt(idstr);
		
		String sql="delete from GOOD where good_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}
	public int delete(Connection con,int id)throws Exception{
		
		String sql="delete from GOOD where good_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}

	public int delete(Connection con,Good good)throws Exception{
		int id = good.getGood_id();
		String sql="delete from GOOD where good_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}


	public ResultSet list(Connection con, Good good, String cclass) throws SQLException {
		/*select * from (
				GOOD natural join GOOD_CLASS natural join CLASS
				)where class_name='秋装'; */
		StringBuffer sb=new StringBuffer("select * from (" + 
										 "GOOD natural join GOOD_CLASS natural join CLASS" + 
										 ") ");
		if(good.getGood_id()!=0){
			sb.append(" and good_id ="+good.getGood_id());
		}

		if(StringUtil.isNotEmpty(good.getGood_name())){
			sb.append(" and good_name like '%"+good.getGood_name()+"%'");
		}
		if(StringUtil.isNotEmpty(good.getGood_descrip())){
			sb.append(" and good_descrip like '%"+good.getGood_descrip()+"%'");
		}
		if(good.getGood_cost()!=0){
			sb.append(" and good_cost = " + good.getGood_cost() );
		}
		if(good.getGood_price()!=0){
			sb.append(" and good_price like '%"+good.getGood_price()+"%'");
		}
		if(StringUtil.isNotEmpty(good.getGood_place())){
			sb.append(" and good_place like '%"+good.getGood_place()+"%'");
		}
		if(StringUtil.isNotEmpty(cclass)){
			sb.append(" and class_name like '%"+cclass+"%'");
		}
		sb.append(" order by good_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}


	public ResultSet list(Connection con,Good good) throws Exception{
		
	 /*int good_id;
	String good_name;
	String good_descrip;
	int good_cost;
	int good_price;
	String good_place;
	ImageIcon good_picture;
	int good_hot;*/
		
		
		StringBuffer sb=new StringBuffer("select * from GOOD");
		if(good.getGood_id()!=0){
			sb.append(" and good_id ="+good.getGood_id());
		}

		if(StringUtil.isNotEmpty(good.getGood_name())){
			sb.append(" and good_name like '%"+good.getGood_name()+"%'");
		}
		if(StringUtil.isNotEmpty(good.getGood_descrip())){
			sb.append(" and good_descrip like '%"+good.getGood_descrip()+"%'");
		}
		if(good.getGood_cost()!=0){
			sb.append(" and good_cost = " + good.getGood_cost() );
		}
		if(good.getGood_price()!=0){
			sb.append(" and good_price like '%"+good.getGood_price()+"%'");
		}
		if(StringUtil.isNotEmpty(good.getGood_place())){
			sb.append(" and good_place like '%"+good.getGood_place()+"%'");
		}
		sb.append(" order by good_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	
	
	
	public ResultSet listForHot(Connection con) throws Exception{
		
		 /*int good_id;
		String good_name;
		String good_descrip;
		int good_cost;
		int good_price;
		String good_place;
		ImageIcon good_picture;
		int good_hot;*/
			
			
			StringBuffer sb=new StringBuffer("select * from GOOD where good_hot = 1");
			sb.append(" order by good_id ");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		public ResultSet listForCustomer(Connection con,Good good) throws Exception{
			StringBuffer sb=new StringBuffer("select cus_id,cus_username,sum(good_num) from CUSTOMER natural join \"ORDER\" " + 
					"natural join ORD_GOO natural join GOOD where good_id=? group by good_id,cus_id,cus_username order by cus_id,good_id ");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			if(good.getGood_id()==0||"".equals(""+good.getGood_id())) {
				return null;
			}
			pstmt.setInt(1, good.getGood_id());
			return pstmt.executeQuery();
		}

		public ResultSet listForNotHot(Connection con) throws Exception{
			
				StringBuffer sb=new StringBuffer("select * from GOOD where good_hot = 0");
				sb.append(" order by good_id ");
				PreparedStatement pstmt=con.prepareStatement(sb.toString());
				return pstmt.executeQuery();
		}
		
		
		public int getGoodSellNum(Connection con,Good good) throws SQLException {
			/*
			select good_id,sum(good_num) from ORD_GOO where good_id=12 group by good_id order by good_id ;*/
			
			
			
			int id = good.getGood_id();
			StringBuffer sb=new StringBuffer("select sum(good_num) from ORD_GOO where good_id=? group by good_id");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			int sum=0;
			
			if(rs.next()) {
				
				sum=rs.getInt("sum(good_num)");
				
			}
			
			return sum;
		}		
		public int getGoodSellNum(Connection con,int id2) throws SQLException {
			/*
			select good_id,sum(good_num) from ORD_GOO where good_id=12 group by good_id order by good_id ;*/
			
			
			
			int id = id2;
			StringBuffer sb=new StringBuffer("select sum(good_num) from ORD_GOO where good_id=? group by good_id");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			int sum=0;
			
			if(rs.next()) {
				
				sum=rs.getInt("sum(good_num)");
				
			}
			
			return sum;
		}		
		public int getGoodSellNum(Connection con,String idstr) throws SQLException {
			/*
			select good_id,sum(good_num) from ORD_GOO where good_id=12 group by good_id order by good_id ;*/
			
			
			
			int id = Integer.parseInt(idstr);
			StringBuffer sb=new StringBuffer("select sum(good_num) from ORD_GOO where good_id=? group by good_id");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			int sum=0;
			
			if(rs.next()) {
				
				sum=rs.getInt("sum(good_num)");
				
			}
			
			return sum;
		}
		
		
		
		

/*int good_id;
String good_name;
String good_descrip;
int good_cost;
int good_price;
String good_place;
ImageIcon good_picture;
int good_hot;*/
	public int update(Connection con,Good good)throws Exception{

		ImageIcon imageIcon = good.getGood_picture();
		Image image = imageIcon.getImage();
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
	    gc.drawImage(image,0,0,null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", bos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_good_update.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(bos.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_good_update.jpg");
	    String sql="update GOOD set "
				+ "good_name=?"
				+ ",good_descrip=?"
				+ ",good_cost=?"
				+ ",good_price=?"
				+ ",good_place=?"
				+ ",good_picture=?"
				+ ",good_hot=?  where "
				+ "good_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,good.getGood_name());
		pstmt.setString(2,good.getGood_descrip());
		pstmt.setInt(3,good.getGood_cost());
		pstmt.setInt(4,good.getGood_price());
		pstmt.setString(5, good.getGood_place());
		pstmt.setBinaryStream(6, str,str.available());
		pstmt.setInt(7,good.getGood_hot());
		pstmt.setInt(8,good.getGood_id());
		return pstmt.executeUpdate();
		
		
	}

	public int addClass(Connection con, List<String> list,int good_id) throws SQLException {
		// TODO Auto-generated method stub
		
		
		String sql = "insert into GOOD_CLASS(class_id,good_id) values(?,?) ";
		int number=0;
		PreparedStatement pstmt =null;
		for(int j = 0 ;j < list.size() ;j++) {
			/*System.out.println("Class_id:    "+classDao.getClassIdByName(con, (String)dtm2.getValueAt(j, 0)));
			System.out.println("Class_name:    "+(String)dtm2.getValueAt(j, 0));
			System.out.println("Good _id:    "+goodDao.getGoodIdByName(con, good.getGood_name()));
			System.out.println("Good_name:    "+(String)dtm2.getValueAt(j, 0));*/
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, classDao.getClassIdByName(con, list.get(j)));
			pstmt.setInt(2, good_id);
			if(pstmt.execute())
				number++;
			pstmt.close();
		}		
		
		return number;
		
		
	}






	
}
