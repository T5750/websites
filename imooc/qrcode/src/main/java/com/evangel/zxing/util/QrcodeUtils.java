package com.evangel.zxing.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 *
 */
public class QrcodeUtils {
	private static final transient Logger LOGGER = LoggerFactory
			.getLogger(QrcodeUtils.class);
	private static transient String DEFAULT_FORMAT = "jpg";
	private static transient int DEFAULT_WIDTH = 200;
	private static transient int DEFAULT_HEIGHT = 200;
	static {
		try {
			final String[] foo = new String[] { "240", "240" };
			final String format = "jpg";
			if (StringUtils.isNotBlank(format)) {
				DEFAULT_FORMAT = StringUtils.strip(format).toLowerCase();
			}
			if (ArrayUtils.isNotEmpty(foo) && foo.length == 2) {
				Integer tmpWidth = Integer.valueOf(foo[0]);
				Integer tmpHeight = Integer.valueOf(foo[1]);
				if (tmpWidth > 0 && tmpHeight > 0) {
					DEFAULT_WIDTH = tmpWidth;
					DEFAULT_HEIGHT = tmpHeight;
				} else {
					LOGGER.warn("qrcode size must be lager than zero.");
				}
			}
		} catch (Throwable e) {
			LOGGER.warn("read default qrcode size config error: ", e);
		}
	}

	/**
	 * 生成二维码（无中间logo）
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            输出文件
	 */
	public static final void gen(final String content, File destFile)
			throws Exception {
		gen(content, destFile, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            目的文件
	 * @param logoFile
	 *            中间logo文件
	 *
	 */
	public static final void gen(final String content, final File destFile,
			final File logoFile) throws Exception {
		gen(content, destFile, logoFile, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            目的文件
	 * @param logoFile
	 *            中间logo文件
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final File destFile,
			final File logoFile, int width, int height) throws Exception {
		FolderUtils.mkdirs(destFile.getParent());
		OutputStream output = null;
		InputStream input = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(destFile));
			if (logoFile != null && logoFile.exists() && logoFile.isFile()) {
				input = new BufferedInputStream(new FileInputStream(logoFile));
			}
			gen(content, output, input, width, height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(input);
		}
	}

	/**
	 * 生成二维码（无中间logo）
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destFile
	 *            输出文件
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, File destFile,
			int width, int height) throws Exception {
		FolderUtils.mkdirs(destFile.getParent());
		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(destFile));
			gen(content, output, width, height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Exception e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 */
	public static final void gen(final String content, final OutputStream output)
			throws Exception {
		gen(content, output, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 */
	public static final void gen(final String content,
			final OutputStream output, final InputStream logoInput)
			throws Exception {
		gen(content, output, logoInput, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content,
			final OutputStream output, final InputStream logoInput, int width,
			int height) throws Exception {
		gen(content, output, logoInput, width, height, ErrorCorrectionLevel.M);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param logoInput
	 *            中间logo输入流，为空时中间无logo
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param errorCorrectionLevel
	 *            容错级别
	 */
	public static final void gen(final String content,
			final OutputStream output, final InputStream logoInput, int width,
			int height, ErrorCorrectionLevel errorCorrectionLevel)
			throws Exception {
		if (StringUtils.isEmpty(content)) {
			throw new IllegalArgumentException(
					"qr code content cannot be empty.");
		}
		if (output == null) {
			throw new IllegalArgumentException(
					"qr code output stream cannot be null.");
		}
		final BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content,
				width, height, errorCorrectionLevel);
		if (logoInput == null) {
			try {
				MatrixToImageWriter.writeToStream(matrix, DEFAULT_FORMAT,
						output);
				return;
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		}
		final MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(
				Color.WHITE, 5);
		final String destPath = FilenameUtils
				.normalizeNoEndSeparator(SystemUtils.getJavaIoTmpDir()
						+ File.separator + UUID.randomUUID().toString()
						+ ".tmp");
		InputStream tmpInput = null;
		final File destFile = new File(destPath);
		try {
			MatrixToImageWriterEx.writeToFile(matrix, DEFAULT_FORMAT, destPath,
					logoInput, logoConfig);
			tmpInput = new BufferedInputStream(new FileInputStream(destFile));
			IOUtils.copy(tmpInput, output);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			IOUtils.closeQuietly(tmpInput);
			destFile.delete();
		}
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param output
	 *            输出流
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content,
			final OutputStream output, int width, int height) throws Exception {
		gen(content, output, null, width, height);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            输出文件路径
	 */
	public static final void gen(final String content, final String destPath)
			throws Exception {
		gen(content, destPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            输出文件路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final String destPath,
			int width, int height) throws Exception {
		gen(content, new File(destPath), width, height);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            目的文件路径
	 * @param logoPath
	 *            中间logo文件路径
	 */
	public static final void gen(final String content, final String destPath,
			final String logoPath) throws Exception {
		gen(content, destPath, logoPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码文本内容
	 * @param destPath
	 *            目的文件路径
	 * @param logoPath
	 *            中间logo文件路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public static final void gen(final String content, final String destPath,
			final String logoPath, int width, int height) throws Exception {
		File foo = new File(destPath);
		File bar = new File(logoPath);
		gen(content, foo, bar, width, height);
	}

	/**
	 * 解析二维码
	 *
	 * @param input
	 *            二维码输入流
	 */
	public static final String parse(InputStream input) throws Exception {
		Reader reader = null;
		BufferedImage image;
		try {
			image = ImageIO.read(input);
			if (image == null) {
				throw new Exception("cannot read image from inputstream.");
			}
			final LuminanceSource source = new BufferedImageLuminanceSource(
					image);
			final BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
					source));
			final Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			// 解码设置编码方式为：utf-8，
			reader = new MultiFormatReader();
			return reader.decode(bitmap, hints).getText();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} catch (ReaderException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		}
	}

	/**
	 * 解析二维码
	 *
	 * @param url
	 *            二维码url
	 */
	public static final String parse(URL url) throws Exception {
		InputStream in = null;
		try {
			in = url.openStream();
			return parse(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 解析二维码
	 *
	 * @param file
	 *            二维码图片文件
	 */
	public static final String parse(File file) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			return parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 解析二维码
	 *
	 * @param filePath
	 *            二维码图片文件路径
	 */
	public static final String parse(String filePath) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			return parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("parse QR code error: ", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
