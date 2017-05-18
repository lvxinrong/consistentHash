package com.lv.photo.photoToBase64a;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImgToBase64Util {

	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//将图片的内容通过FileInputStream流读取到 data中，通过Base64.encodeBase64()方法转换成base64格式
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 解码的时候要注意图片的类型就是后缀名
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		String imgFile = "d:\\2017.png";// 待处理的图片
		String imgbese = getImgStr(imgFile);
		//System.out.println(imgbese.length());
		System.out.println(imgbese);
		String imgFilePath = "d:\\332.jpg";// 新生成的图片
		generateImage(imgbese, imgFilePath);
	}
}
