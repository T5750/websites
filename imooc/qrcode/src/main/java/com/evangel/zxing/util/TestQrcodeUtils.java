package com.evangel.zxing.util;

import java.io.File;

public class TestQrcodeUtils {
	public static void main(String[] args) {
		try {
			int width = 300;
			int height = 300;
			String content = "http://m.imooc.com/search/?words=二维码";
			File file = new File("D:/code/img-util.png");
			QrcodeUtils.gen(content, file);
			QrcodeUtils.gen(content, "D:/code/img-logo.png",
					"D:/code/logo.png", width, height);
			String result = QrcodeUtils.parse(file);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
