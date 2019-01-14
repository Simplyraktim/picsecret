package com.raktim.picsecret;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;


public class Steganography
{
	
	public Steganography() {}

	// Encodes secret to bitmap from here
	public static Bitmap encode(Bitmap bmp, String secret)
	{
		int height = bmp.getHeight();
		int width = bmp.getWidth();
		
		Bitmap newImage = null;
		int[] imgPixels = new int[width * height];
		bmp.getPixels(imgPixels, 0, width, 0, 0, width, height);
		int density = bmp.getDensity();
		bmp.recycle();
		try
		{
			byte[] byteImage = LSB2bit.encodeMessage(imgPixels, width, height, secret);
			
			newImage = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			newImage.setDensity(density);
			int imgMod[] = LSB2bit.byteArrayToIntArray(byteImage);
			int masterIndex = 0;
			for (int j = 0; j < height; j++)
				for (int i = 0; i < width; i++){
                    // The unique way to write correctly the sourceBitmap, android bug!!!
                    newImage.setPixel(i, j, Color.argb(0xFF,
                    		imgMod[masterIndex] >> 16 & 0xFF,
                    		imgMod[masterIndex] >> 8 & 0xFF,
                    		imgMod[masterIndex++] & 0xFF));
				}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return newImage;
	}
	
	// Decodes secret from bitmap from here
	public static String decode(Bitmap bmp)
	{
		byte[] b = null;
		
		try 
		{
			int[] pixels = new int[bmp.getWidth() * bmp.getHeight()];
			bmp.getPixels(pixels, 0, bmp.getWidth(), 0, 0, bmp.getWidth(), bmp.getHeight());
			b = LSB2bit.convertArray(pixels);
		} 
		catch (OutOfMemoryError er) 
		{
			System.out.println( "Image too large, out of memory!");
		}
		
		final String vvv = LSB2bit.decodeMessage(b, bmp.getWidth(), bmp.getHeight());
		
		return vvv;
	}

}