package com.evangel.qrcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	public static void main(String[] args) throws Exception {
		Qrcode x = new Qrcode();
		x.setQrcodeErrorCorrect('M');// 纠错等级
		x.setQrcodeEncodeMode('B'); // N代表数字；A代表a—Z；B代表其他字符
		x.setQrcodeVersion(7); // 版本号（1-40）
		String qrData = "http://www.imooc.com";
		int width = 67 + 12 * (7 - 1);
		int height = 67 + 12 * (7 - 1);
		// 依托javaGUI的画图工具实现的
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);
		int pixoff = 2;// 偏移量
		byte[] d = qrData.getBytes("gb2312");
		if (d.length > 0 && d.length < 120) {
			boolean[][] s = x.calQrcode(d);
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[i][j]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		}
		gs.dispose();
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File("D:/code/qrcode.png"));
	}
}
