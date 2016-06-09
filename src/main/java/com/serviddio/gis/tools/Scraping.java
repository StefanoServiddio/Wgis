package com.serviddio.gis.tools;


import java.awt.image.BufferedImage;

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
	public static void getDataInfo() throws InterruptedException {
		String relLink = "";
		try {
			// fetch the document over HTTP
			Document doc = Jsoup.connect("http://www.protezionecivile.gov.it/jcms/it/allertamento_meteo_idro.wp").get();

			// get the page title
			String title = doc.title();
			System.out.println("title: " + title);

			// get all links in page
			Elements contents = doc.select("span.viewall > a[href]");

			for (Element cont : contents) {
				// get the value from the href attribute
				relLink = cont.attr("href");
				if (Pattern.matches("view_bcr.wp.*", relLink))

					System.out.println("link: " + relLink);

			}
			doc = Jsoup.connect("http://www.protezionecivile.gov.it/jcms/it/" + relLink).get();
			title = doc.title();
			System.out.println("title: " + title);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// WebDriver driver = new FirefoxDriver();
		// driver.get("http://www.protezionecivile.gov.it/jcms/it/" + relLink);

	}

	public static BufferedImage getImageFromURL(String url) {
		try {
			URL urlPath;

			urlPath = new URL(url);
			
			BufferedImage img = ImageIO.read(urlPath);
			return img;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("URL inesistente");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public static String getDate(){
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		 String strDate = sdf.format(date);
		 System.out.println("data di oggi:"+strDate );
		 return strDate;
		 
		 
		 
		    
		
	}
}
