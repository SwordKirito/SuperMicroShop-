package com.sms.dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sms.model.Customer;
import com.sms.model.Employee;
import com.sms.util.StringUtil;
import com.sms.view.PhotoTest;


public class EmployeeDao {
	
	
	
	

	
	public Employee searchByID(Connection con,String id) throws SQLException {
		
		Employee employee = new Employee();
		ByteArrayOutputStream baos = null;
		ByteArrayOutputStream bos = null;
		Icon IC = new ImageIcon();
		
		StringBuffer sb=new StringBuffer("select * from EMPLOYEE");
		if(StringUtil.isNotEmpty(id)){
			sb.append(" and emp_id ="+id);
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			
			InputStream is = rs.getBinaryStream("emp_photo");
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
			

			int emp_id  = -1 ;
			String emp_name = null;
			String emp_pwd = null;
			int emp_age  = -1 ;
			int emp_salary  = -1 ;
			int emp_work_time  = -1 ;
			int shop_id  = 0 ;
			String emp_tele = null;
			String emp_qq = null;
			
	
			emp_id  = rs.getInt("emp_id") ;
			emp_name = rs.getString("emp_name");
			emp_pwd = rs.getString("emp_pwd");
			emp_age  = rs.getInt("emp_age") ;
			emp_salary  = rs.getInt("emp_salary") ;
			emp_work_time  = rs.getInt("emp_work_time") ;
			shop_id  = rs.getInt("shop_id") ;
			emp_tele = rs.getString("emp_tele");
			emp_qq = rs.getString("emp_qq");
			
			
			
			employee.setEmp_id(emp_id);
			employee.setEmp_name(emp_name);
			employee.setEmp_pwd(emp_pwd);
			employee.setEmp_age(emp_age);
			employee.setEmp_salary(emp_salary);
			employee.setEmp_work_time(emp_work_time);
			employee.setShop_id(shop_id);
			employee.setEmp_tele(emp_tele);
			employee.setEmp_qq(emp_qq);
			employee.setEmp_photo((ImageIcon) IC);
		}
		
		return employee;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Employee UserLogin(Connection con,Employee user) throws SQLException{
		
		Employee resultUser=null;

		/*String sqlString="select * from EMPLOYEE where 'emp_name'=? and 'emp_pwd'=?";*/
		String sqlString="select * from EMPLOYEE where emp_name=? and emp_pwd=?";
		
		PreparedStatement pstmt = con.prepareStatement(sqlString);			
		pstmt.setString(1,user.getEmp_name());
		pstmt.setString(2,user.getEmp_pwd());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			
			resultUser=new Employee();

			resultUser.setEmp_pwd(rs.getString("emp_pwd"));
			
			resultUser.setEmp_name(rs.getString("emp_name"));
			
			resultUser.setEmp_id(rs.getInt("emp_id"));

			resultUser.setEmp_age(rs.getInt("emp_age"));
			
			resultUser.setEmp_salary(rs.getInt("emp_salary"));
			
			resultUser.setEmp_work_time(rs.getInt("emp_work_time"));

			resultUser.setShop_id(rs.getInt("shop_id"));
			
			resultUser.setEmp_tele(rs.getString("emp_tele"));

			resultUser.setEmp_qq(rs.getString("emp_qq"));
			
			Blob blob = rs.getBlob( "emp_photo");
			
			BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());

			 byte[] bytes = new byte[(int) blob.length()];
			 int len = bytes.length;

			 int offset = 0;

			 int read = 0;

			 try {
				while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {

				 offset += read;

				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resultUser.setEmp_photo(new ImageIcon(bytes));
			
	//		resultUser.setEmp_photo( new ImageIcon( rs.getBlob( "\"emp_photo\"") ) );
			
			
			
			
			
			/*
			resultUser.setEmp_pwd(rs.getString("emp_pwd"));
			
			resultUser.setEmp_name(rs.getString("emp_name"));
			
			resultUser.setEmp_id(rs.getInt("emp_id"));*/
			
			//System.out.println(resultUser.getEmp_id()+"  "+resultUser.getEmp_name()+"  "+resultUser.getEmp_pwd()+"  ");
		}
		
		return resultUser;
	}
	public boolean isBoss(Connection con,Employee user) throws SQLException{
		
		String sqlString="select boss_id from SHOP";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		
		//System.out.println(user.getEmp_id()+"  "+user.getEmp_name()+"  "+user.getEmp_pwd()+"  ");

		if(rs.next()){
			//System.out.println(rs.getInt("boss_id"));
			if(rs.getInt("boss_id")==user.getEmp_id()) {
				return true;
			}
		}
		return false;
	}
	public int add(Connection con, Employee employee) throws SQLException, IOException {
		// TODO Auto-generated method stub
		

		//图片存到文件中并获取文件输入流
		ImageIcon imageIcon = employee.getEmp_photo();
		Image image = imageIcon.getImage();
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
        gc.drawImage(image,0,0,null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", bos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_employee_add.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(bos.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_employee_add.jpg");

		
		
		String emp_name=employee.getEmp_name();
		String emp_pwd=employee.getEmp_pwd();
		int emp_age=employee.getEmp_age();
		int emp_salary=employee.getEmp_salary();
		int emp_work_time=employee.getEmp_work_time();
		int shop_id=employee.getShop_id();
		String emo_tele = employee.getEmp_tele();
		String emp_qq = employee.getEmp_qq();
	

		
	    String sql="insert into EMPLOYEE values(null,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,emp_name);
		pstmt.setString(2,emp_pwd);
		pstmt.setInt(3,emp_age);
		pstmt.setInt(4,emp_salary);
		pstmt.setInt(5,emp_work_time);
		pstmt.setInt(6,shop_id);
		pstmt.setString(7,emo_tele);
		pstmt.setString(8,emp_qq);
		pstmt.setBinaryStream(9,str,str.available());
		
		
		
		return pstmt.executeUpdate();
		
	}

	public int delete(Connection con,String idstr)throws Exception{
		
		int id = Integer.parseInt(idstr);
		
		String sql="delete from EMPLOYEE where emp_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}
	public int delete(Connection con,int id)throws Exception{
		
		String sql="delete from EMPLOYEE where emp_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}

	public int delete(Connection con,Employee employee)throws Exception{
		
		int id = employee.getEmp_id();
		String sql="delete from EMPLOYEE where emp_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		return pstmt.executeUpdate();
	}





public ResultSet list(Connection con,Employee employee) throws Exception{
		
	
	/*	private JTextField s_id_text;
	private JTextField s_age_text;
	private JTextField s_work_time_text;
	private JTextField s_name_text;
	private JTextField s_salary_text;
	private JTextField s_tele_text;
	private JTextField s_qq_text;
	
	
	private JTextField m_id_text;
	private JTextField m_name_text;
	private JTextField m_age_text;
	private JTextField m_salary_text;
	private JTextField m_tele_text;
	private JTextField m_qq_text;
	private JTextField m_work_time_text;
	private JTable table_1;
	private JTextField m_shop_id_text;
	private JLabel m_photo_lable ;
	
	
	
	private int emp_id;
	private String emp_name;
	private String emp_pwd;

	private int emp_age;
	private int emp_salary;
	private int emp_work_time;

	private int shop_id;
	private String emp_tele;
	private String emp_qq;

	private ImageIcon emp_photo ;
	*/
		
		
		StringBuffer sb=new StringBuffer("select * from EMPLOYEE");
		if(employee.getEmp_id()!=0){
			sb.append(" and emp_id ="+employee.getEmp_id());
		}
		
		if(StringUtil.isNotEmpty(employee.getEmp_name())){
			sb.append(" and emp_name like '%"+employee.getEmp_name()+"%'");
		}
		if(employee.getEmp_age()!=0){
			sb.append(" and emp_age = " + employee.getEmp_age() );
		}
		if(employee.getEmp_salary()!=0){
			sb.append(" and emp_salary like '%"+employee.getEmp_salary()+"%'");
		}
		if(employee.getEmp_work_time()!=0){
			sb.append(" and emp_work_time like '%"+employee.getEmp_work_time()+"%'");
		}
		if(employee.getShop_id()!=0){
			sb.append(" and shop_id like '%"+employee.getShop_id()+"%'");
		}
		if(StringUtil.isNotEmpty(employee.getEmp_tele())){
			sb.append(" and emp_tele like '%"+employee.getEmp_tele()+"%'");
		}
		if(StringUtil.isNotEmpty(employee.getEmp_qq())){
			sb.append(" and emp_qq like '%"+employee.getEmp_qq()+"%'");
		}
		sb.append(" order by emp_id ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}



/*private int emp_id;
private String emp_name;
private String emp_pwd;

private int emp_age;
private int emp_salary;
private int emp_work_time;

private int shop_id;
private String emp_tele;
private String emp_qq;

private ImageIcon emp_photo ;*/
	public int update(Connection con,Employee employee)throws Exception{

		ImageIcon imageIcon = employee.getEmp_photo();
		Image image = imageIcon.getImage();
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
	    gc.drawImage(image,0,0,null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", bos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_employee_update.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(bos.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_employee_update.jpg");
	    String sql="update EMPLOYEE set "
				+ "emp_name=?"
				+ ",emp_pwd=?"
				+ ",emp_age=?"
				+ ",emp_salary=?"
				+ ",emp_work_time=?"
				+ ",shop_id=?"
				+ ",emp_tele=?"
				+ ",emp_qq=?"
				+ ",emp_photo=?"
				+ "  where emp_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,employee.getEmp_name());
		pstmt.setString(2,employee.getEmp_pwd());
		pstmt.setInt(3,employee.getEmp_age());
		pstmt.setInt(4,employee.getEmp_salary());
		pstmt.setInt(5, employee.getEmp_work_time());
		pstmt.setInt(6, employee.getShop_id());
		pstmt.setString(7, employee.getEmp_tele());
		pstmt.setString(8,employee.getEmp_qq());
		pstmt.setBinaryStream(9,str,str.available());
		pstmt.setInt(10,employee.getEmp_id());
		return pstmt.executeUpdate();
		
		
	}
}
