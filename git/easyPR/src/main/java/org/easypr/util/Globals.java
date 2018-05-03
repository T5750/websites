package org.easypr.util;

import java.io.File;

/**
 * @author T5750
 */
public class Globals {
	public static String CLASSPATH = Thread.currentThread()
			.getContextClassLoader().getResource("").getPath();
	static {
		CLASSPATH = CLASSPATH.substring(1, CLASSPATH.length());
	}

	/**
	 * img, xml转为Windows路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static String convertPath(String filePath) {
		filePath = Globals.CLASSPATH + filePath;
		filePath = filePath.replaceAll("/", "\\\\");
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		return filePath;
	}
}
