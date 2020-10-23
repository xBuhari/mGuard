package me.xBuhari.MGuard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

public class Sifreleme {
	
	private Turler sifrelemeTuru;
	
	public Sifreleme(Turler tur) {
		this.sifrelemeTuru = tur;
	}
	
	public String code(String txt) {
		String codedtxt = txt;
		if (this.sifrelemeTuru == Turler.Base64) {
			codedtxt = new String(Base64.getEncoder().encode(codedtxt.getBytes()));
		}
		if (this.sifrelemeTuru == Turler.MD5) {
		   try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(codedtxt.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        codedtxt = sb.toString();
		    } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		}
		if (this.sifrelemeTuru == Turler.SHA256) {
			codedtxt = DigestUtils.sha256Hex(codedtxt);   
		}
		if (this.sifrelemeTuru == Turler.SHA1) {
			codedtxt = DigestUtils.sha1Hex(codedtxt);   
		}
		return codedtxt;
	}
	
	public enum Turler {
		SHA256(1),
		SHA1(2),
		MD5(3),
		Base64(4);
		
		int tur;
		
		Turler(Integer i) {
			this.tur = i;
		}
	}

}
