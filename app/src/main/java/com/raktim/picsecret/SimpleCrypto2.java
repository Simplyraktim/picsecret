package com.raktim.picsecret;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class SimpleCrypto2 {
	public static int extra;
	public static void setExtra(int val)
	{
		extra = val;
	}
	public static int getExtra()
	{
		return extra;
	}
	public  String encrypt(String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			//Log.d ("raktim", "encrypted : " + String.valueOf(encrypted));

			return android.util.Base64.encodeToString(encrypted, android.util.Base64.URL_SAFE);
//            System.out.println("encrypted string: "
//                    + Base64.encodeBase64String(encrypted));
//
//            return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}


	public  String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			Base64 base64 = new Base64();
			byte[] input = android.util.Base64.decode(encrypted, android.util.Base64.URL_SAFE);

			byte[] original = cipher.doFinal(input);
			//Log.d("raktim", original.toString());

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}