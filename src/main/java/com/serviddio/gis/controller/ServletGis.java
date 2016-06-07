package com.serviddio.gis.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serviddio.gis.tools.Scraping;


/**
 * Servlet implementation class Gis
 */
public class ServletGis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			Scraping.getDataInfo();
			
			BufferedImage img=Scraping.getImageFromURL("http://www.protezionecivile.gov.it/resources/cms/images/"+
			Scraping.getDate()
		    +"_oggi_bcr_d0.jpg");
			if(img==null)
			{ 
				System.out.println("nessuna immagine trovata, avvio secondo tentativo");
				img=Scraping.getImageFromURL("http://www.protezionecivile.gov.it/resources/cms/images/"+
						Scraping.getDate()
					    +"_domani_bcr_d0.jpg");
			}
			ImageIO.write(img, "jpg", new File("/home/stefano/workspace/Wgis/src/main/webapp/assets/img/allertaOggi.jpg"));
			
			if(img!=null)
		   {
			   System.out.println("Immagine caricata con successo");
		   }
		
		} catch (InterruptedException e) {
			System.out.println("problemi scraping");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/gis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}