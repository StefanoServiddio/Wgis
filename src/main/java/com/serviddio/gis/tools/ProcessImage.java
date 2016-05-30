package com.serviddio.gis.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcessImage {
	 private BufferedImage image;
	
	public void crop(){
	    int x = 0;
	    int y = 142;
	    int w = 600;
	    int h = 850-y;

	    String path = "/home/stefano/workspace/Wgis/src/main/webapp/assets/img/allertaOggi.jpg";
	    String path2 = "/home/stefano/workspace/Wgis/src/main/webapp/assets/img/allertaOggiEdit.jpg";
	    

	  
		try {
			image = ImageIO.read(new File(path));
		
	    BufferedImage out = image.getSubimage(x, y, w, h);

	   
			ImageIO.write(out, "jpg", new File(path2));
			System.out.println("Immagine scritta nel percorso scelto");
			image = ImageIO.read(new File(path2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void getRGBCol()
	{
		 int clr=  image.getRGB(303,232); 
		  int  red   = (clr & 0x00ff0000) >> 16;
		  int  green = (clr & 0x0000ff00) >> 8;
		  int  blue  =  clr & 0x000000ff;
		  System.out.println("Red Color value = "+ red);
		  System.out.println("Green Color value = "+ green);
		  System.out.println("Blue Color value = "+ blue);
	}
	
	public static void main(String[] args){
		ProcessImage pi= new ProcessImage();
		pi.crop();
		pi.getRGBCol();
		
		
	}
}
