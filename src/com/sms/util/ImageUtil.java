package com.sms.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {  
  
	public static Image changeSize(int fwidth,int fheight,File imageFile) throws IOException {
		
		
		BufferedImage src = ImageIO.read(imageFile);  
	    int width = fwidth;  
	    int height = fheight;  
	    Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPEG", bos);  
	    FileOutputStream out = new FileOutputStream("D://result.jpg");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(bos.toByteArray());    
	    out.close();  
		return null;
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
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像    
	    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();    
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPEG", bos);  
	    FileOutputStream out = new FileOutputStream("D://result.jpg");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(bos.toByteArray());    
	    out.close();  
		return null;
	}
	
	
	
	
	
	public void test() throws Exception {  
	    File imageFile = new File("D://1.jpg");  
	    BufferedImage src = ImageIO.read(imageFile);  
	    int width = src.getWidth()/2;  
	    int height = src.getHeight()/2;  
	    Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);    
	    BufferedImage desc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //缩放图像    
	    Graphics g = desc.getGraphics();    
	    g.drawImage(image, 0, 0, null); // 绘制缩小后的图    
	    g.dispose();         
	  
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    ImageIO.write(desc, "JPEG", bos);  
	    FileOutputStream out = new FileOutputStream("D://result.jpg");    
	    //bos.toByteArray()  你想要的数组  
	    out.write(bos.toByteArray());    
	    out.close();    
	}  
    public static void main(String[] args) {
        

        try {
			changeSize(20,20,new File("D://1.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//
//        long start=System.currentTimeMillis();
//        String filePath = "C:\\Users\\zhangmi\\Desktop\\资料";
//        changeImgSize(new File(filePath));
//
//        long end=System.currentTimeMillis();
//        //在最好的一行加上:
//        System.out.println("执行耗时 : "+(end-start)/1000f+" 秒 ");
        
    }
}  