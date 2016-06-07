package com.serviddio.gis.tools;



import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.CRS;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class TransformPoints {
	
	private final static String rifSrc="EPSG:4326";
	private final static String rifDef="EPSG:3857";
//	public static void main(String[] args){
//		DirectPosition2D points=TransformPoints.convert();
//	}
	
	
	public static DirectPosition2D convert(double x, double y) {
		try {
			CoordinateReferenceSystem sourceCrs = CRS.decode(rifSrc);
			CoordinateReferenceSystem targetCrs = CRS.decode(rifDef);
				
				
				
							
				 
				boolean lenient = true;
				MathTransform mathTransform 
				    = CRS.findMathTransform(sourceCrs, targetCrs, lenient);
				 
				DirectPosition2D srcDirectPosition2D 
				    = new DirectPosition2D(sourceCrs, x, y);
				DirectPosition2D destDirectPosition2D 
				    = new DirectPosition2D();
				mathTransform.transform(srcDirectPosition2D, destDirectPosition2D);
						
				return destDirectPosition2D;
			} catch (NoSuchAuthorityCodeException e) {
				
				e.printStackTrace();
			} catch (FactoryException e) {
				
				e.printStackTrace();
			} catch (MismatchedDimensionException e) {
				
				e.printStackTrace();
			} catch (TransformException e) {
				
				e.printStackTrace();
			}
			return null;
			
			
			

	}
}
