package com.sms.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PhotoUtil {
	public static ByteArrayOutputStream changeSize(int fwidth,int fheight,ByteArrayOutputStream baos) throws IOException {
		
	    FileOutputStream out1 = new FileOutputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.png");    
	    out1.write(baos.toByteArray());
	    out1.close();
	    File imageFile = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.png");
		BufferedImage bufferedImage = ImageIO.read(imageFile);  
	    int width = fwidth;
	    int height = fheight;
	    Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像   
	    
	    
	    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "PNG", bos);  
	    FileOutputStream out = new FileOutputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.png");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(bos.toByteArray());    
	    out.close();  	   
		return bos;	
	}
	
	
	public static File changeSize(int fwidth,int fheight,File imageFile) throws IOException {
		
		
		BufferedImage src = ImageIO.read(imageFile);  
	    int width = fwidth;  
	    int height = fheight;  
	    Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "PNG", bos);  
	    FileOutputStream out = new FileOutputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.png");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(bos.toByteArray());    
	    out.close();  
		return new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.png");
		
	}
	
	
	
	public static ImageIcon changeSize(int fwidth,int fheight,ImageIcon imageIcon) throws IOException {
		
		
	
		Image image = imageIcon.getImage();
		BufferedImage bufferedImage = new BufferedImage(imageIcon.getImage().getWidth(null), imageIcon
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics gc = bufferedImage.createGraphics();
	    gc.drawImage(image,0,0,null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	    ImageIO.write(bufferedImage, "JPG", baos);  
	    File f = new File("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.jpg");
	    FileOutputStream out = new FileOutputStream(f);    
	    out.write(baos.toByteArray());    
	    out.close();  
	    FileInputStream str = new FileInputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.jpg");
	
	
	
		BufferedImage src = ImageIO.read(str);  
	    int width = fwidth;  
	    int height = fheight;  
	    Image image2 = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image2, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPG", bos);  
	    FileOutputStream out2 = new FileOutputStream("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.jpg");    
	    //bos.toByteArray()  你想要的数组  
	    out2.write(bos.toByteArray());    
	    out2.close();  
		return new ImageIcon("E://程序试做//软件开发综合实践//SuperMicroShop//cache_pictures//cache_picture_changeSize.jpg");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
