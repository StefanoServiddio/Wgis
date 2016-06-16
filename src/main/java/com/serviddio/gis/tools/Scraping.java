package com.serviddio.gis.tools;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;


public class Scraping {
	private static boolean lunchScriptOnlyTimes=true;

	public static boolean getImageFromURL(String url) {
		try {
			URL urlPath;

			urlPath = new URL(url);
			
			BufferedImage img = ImageIO.read(urlPath.openStream());
			if(img!=null)
			{
			ImageIO.write(img, "jpg", new File("/home/stefano/Immagini/allertaOggi.jpg"));
			ProcessImage pi= new ProcessImage();
			pi.crop();
			if(lunchScriptOnlyTimes)
			{
			Runtime.getRuntime().exec("/home/stefano/workspace/Wgis/src/main/webapp/WEB-INF/script/script_gdal");
			lunchScriptOnlyTimes=false;
			}
			return true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("URL inesistente");
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;

	}
	public static String getDate(){
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		 String strDate = sdf.format(date);
		 System.out.println("data di oggi:"+strDate );
		 return strDate;
		 
		 
		 
		    
		
	}
}
