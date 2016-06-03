package com.serviddio.gis.tools;


import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


public class TransformPoints {

	public static void convert(){
		try {
			CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
			CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

}
}
