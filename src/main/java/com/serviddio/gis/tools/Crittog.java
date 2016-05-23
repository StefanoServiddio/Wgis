package com.serviddio.gis.tools;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Crittog {
	private SecretKey key; 
	private String initVector = "RandomInitVector"; 
    private static Crittog istance=null;
    
	private Crittog() {
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
		    key = keyGen.generateKey();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getInitVector() {
		return initVector;
	}

	public void setInitVector(String initVector) {
		this.initVector = initVector;
	}

	public String encrypt(String value) {
		try {
			
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			
			byte[] encrypted = cipher.doFinal(value.getBytes());
			System.out.println("encrypted string: " + Base64.encodeBase64String(encrypted));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public String decrypt(String encrypted) {
		try {
			
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	public static synchronized Crittog getIstance() {
        if (istance == null) {
        	istance = new Crittog();
        }
        return istance;
    }
//	public static void main(String[] args) {
//
//		Crittog c = new Crittog();
//		System.out.println(c.decrypt( c.encrypt( "")));
//	}

}
