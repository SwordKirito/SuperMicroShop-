package com.sms.dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class PhotoDao {

	
	
	
	
	public Icon downloadPhoto(String EMPNO,String PHOTO_FORMAT) {
		Icon IC = new ImageIcon();
		ByteArrayOutputStream baos = null;
		try {
			Connection conn = createConnection();
		
			PreparedStatement ps = conn.prepareStatement("select PICTURE from db2admin.EMP_PHOTO where EMPNO=? and PHOTO_FORMAT=?");// 从数据库中读取的图片是事先已经存入的二进制数据

			ps.setString(1, EMPNO);
			ps.setString(2, PHOTO_FORMAT);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				InputStream is = rs.getBinaryStream("PICTURE");
				baos = new ByteArrayOutputStream();
				int b = 0;
				while ((b = is.read()) != -1) {
					baos.write(b);
				}
			}
			// 将数据流转为Icon数据
			IC = new ImageIcon(baos.toByteArray());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return IC;

	}
	
	
	
	
	
	
	private Connection createConnection() {
		
		Connection conn = null;
		
		try {
			String url = "jdbc:db2:sample";

			String username = "db2admin";
			
			String password = "db2admin";
			
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			
			conn = DriverManager.getConnection(url,username,password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	
	
	
	
	
	
	public void setPhoto(String filename) {

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:sms", "system", "123456");

			File f = new File(filename);
			changeSize(80,80,f);
			FileInputStream str = new FileInputStream("cacheImage2.jpg"); //filename
			
			
			
			/*String sql = "insert into picture(id,image) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "1"); //
			pstmt.setBinaryStream(2, str, str.available()); //
*/			
			
			String sql = "insert into picture(image) values(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
	//		pstmt.setString(1, "1"); // 
			pstmt.setBinaryStream(1, str, str.available()); //
			
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *  
	 * @return 
	 */

	public Icon getIcon() {
		Icon IC = new ImageIcon();
		ByteArrayOutputStream baos = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:sms", "system", "123456");
			Statement stmt = con.createStatement();
			String sql = "select image from picture WHERE id='9'";

			PreparedStatement ps = con
					.prepareStatement("select Image from picture where id='1'");
			ResultSet rs = ps.executeQuery();

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				InputStream is = rs.getBinaryStream("image");
				baos = new ByteArrayOutputStream();
				int b = 0;
				while ((b = is.read()) != -1) {
					baos.write(b);
				}
			}
			
			IC = new ImageIcon(baos.toByteArray());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return IC;

	}



	
	
	public static File changeSize(int fwidth,int fheight,File imageFile) throws IOException {
		
		
		BufferedImage src = ImageIO.read(imageFile);  
	    int width = fwidth;  
	    int height = fheight;  
	    Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null);  
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPEG", bos);  
	    FileOutputStream out = new FileOutputStream("cacheImage2.jpg");    
	    //bos.toByteArray()  
	    out.write(bos.toByteArray());    
	    out.close();  
		return new File("cacheImage2.jpg");
		
	}
	public static ByteArrayOutputStream changeSize(int fwidth,int fheight,ByteArrayOutputStream baos) throws IOException {
		
	    FileOutputStream out1 = new FileOutputStream("cacheImage.jpg");    
	    out1.write(baos.toByteArray());
	    File imageFile = new File("cacheImage.jpg");
	    out1.close();
		BufferedImage bufferedImage = ImageIO.read(imageFile);  
	    int width = fwidth;
	    int height = fheight;
	    Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null);
	    g.dispose();    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPEG", bos);  
	    //FileOutputStream out = new FileOutputStream("D://result.jpg");    
	    //out.write(bos.toByteArray());    
	    //out.close();  
	    
		return bos;
		
	}
}
