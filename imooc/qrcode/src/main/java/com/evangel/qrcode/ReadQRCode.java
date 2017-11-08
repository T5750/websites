package com.evangel.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRCode {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/code/qrcode.png");
		BufferedImage bufferedImage = ImageIO.read(file);
		QRCodeDecoder coderDecoder = new QRCodeDecoder();
		String result = new String(coderDecoder.decode(new MyQRCodeImage(
				bufferedImage)), "gb2312");
		System.out.println(result);
	}
}
