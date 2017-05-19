package com.lv.photo.photoToBase64a;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImgToBrinaryUtil {

	public static byte[] PhotoToBrinary(String photoPath) {
		
        byte[] data = null;
        try(InputStream in = new FileInputStream(photoPath)){
        	data = new byte[in.available()];
        	in.read(data);
        	in.close();
        }catch(IOException e) {
        	e.printStackTrace();
        }
		return data;
	}
	
	public static void BrinayArrayToPhoto(byte[] data) {
		String filePath = "d:\\123111.png";
		try {
			OutputStream out = new FileOutputStream(filePath);
			out.write(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String... args) {
		String path = "d:\\2017.png";
		byte[] data = PhotoToBrinary(path);
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
		BrinayArrayToPhoto(data);
		
	}

}
