package com.evangel.zxing.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

class MatrixToImageWriterEx {
	private static final MatrixToLogoImageConfig DEFAULT_CONFIG = new MatrixToLogoImageConfig();

	/**
	 * 根据内容生成二维码数据
	 *
	 * @param content
	 *            二维码文字内容[为了信息安全性，一般都要先进行数据加密]
	 * @param width
	 *            二维码照片宽度
	 * @param height
	 *            二维码照片高度
	 * @param errorCorrectionLevel
	 *            纠错等级
	 * @return a {@link com.google.zxing.common.BitMatrix} object.
	 * @since 0.0.7
	 */
	public static BitMatrix createQRCode(String content, int width, int height,
			ErrorCorrectionLevel errorCorrectionLevel) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 设置字符编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
		hints.put(EncodeHintType.MARGIN, 2);
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return matrix;
	}

	/**
	 * 根据内容生成二维码数据
	 *
	 * @param content
	 *            二维码文字内容[为了信息安全性，一般都要先进行数据加密]
	 * @param width
	 *            二维码照片宽度
	 * @param height
	 *            二维码照片高度
	 * @return a {@link com.google.zxing.common.BitMatrix} object.
	 * @since 0.0.7
	 */
	public static BitMatrix createQRCode(String content, int width, int height) {
		return createQRCode(content, width, height, ErrorCorrectionLevel.H);
	}

	/**
	 * 写入二维码、以及将照片logo写入二维码中
	 *
	 * @param matrix
	 *            要写入的二维码
	 * @param format
	 *            二维码照片格式
	 * @param imagePath
	 *            二维码照片保存路径
	 * @param logoPath
	 *            logo路径
	 * @throws java.io.IOException
	 *             if any.
	 * @since 0.0.7
	 */
	public static void writeToFile(BitMatrix matrix, String format,
			String imagePath, String logoPath) throws IOException {
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(logoPath));
			writeToFile(matrix, format, imagePath, input);
		} catch (IOException e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(input);
		}
	}

	/**
	 * <p>
	 * writeToFile.
	 * </p>
	 *
	 * @param matrix
	 *            a {@link com.google.zxing.common.BitMatrix} object.
	 * @param format
	 *            a {@link java.lang.String} object.
	 * @param imagePath
	 *            a {@link java.lang.String} object.
	 * @param logoInputStream
	 *            a {@link java.io.InputStream} object.
	 * @throws java.io.IOException
	 *             if any.
	 * @since 0.0.7
	 */
	public static void writeToFile(BitMatrix matrix, String format,
			String imagePath, InputStream logoInputStream) throws IOException {
		MatrixToImageWriter.writeToPath(matrix, format,
				new File(imagePath).toPath(), new MatrixToImageConfig());
		// 添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
		BufferedImage img = ImageIO.read(new File(imagePath));
		MatrixToImageWriterEx.overlapImage(img, format, imagePath,
				logoInputStream, DEFAULT_CONFIG);
	}

	/**
	 * 写入二维码、以及将照片logo写入二维码中
	 *
	 * @param matrix
	 *            要写入的二维码
	 * @param format
	 *            二维码照片格式
	 * @param imagePath
	 *            二维码照片保存路径
	 * @param logoPath
	 *            logo路径
	 * @param logoConfig
	 *            logo配置对象
	 * @throws java.io.IOException
	 *             if any.
	 * @since 0.0.7
	 */
	public static void writeToFile(BitMatrix matrix, String format,
			String imagePath, InputStream logoPath,
			MatrixToLogoImageConfig logoConfig) throws IOException {
		MatrixToImageWriter.writeToPath(matrix, format,
				new File(imagePath).toPath(), new MatrixToImageConfig());
		// 添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
		BufferedImage img = ImageIO.read(new File(imagePath));
		MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath,
				logoConfig);
	}

	/**
	 * 将照片logo添加到二维码中间
	 *
	 * @param image
	 *            生成的二维码照片对象
	 * @param imagePath
	 *            照片保存路径
	 * @param imagePath
	 *            照片保存路径
	 * @param imagePath
	 *            照片保存路径
	 * @param imagePath
	 *            照片保存路径
	 * @param imagePath
	 *            照片保存路径
	 * @param imagePath
	 *            照片保存路径
	 * @param logoInputStream
	 *            logo输入流
	 * @param formate
	 *            照片格式
	 * @param logoConfig
	 *            a {@link cn.yicha.commons.qrcode.MatrixToLogoImageConfig}
	 *            object.
	 * @since 0.0.7
	 */
	public static void overlapImage(BufferedImage image, String formate,
			String imagePath, InputStream logoInputStream,
			MatrixToLogoImageConfig logoConfig) {
		try {
			BufferedImage logo = ImageIO.read(logoInputStream);
			Graphics2D g = image.createGraphics();
			// 考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
			int width = image.getWidth() / logoConfig.getLogoPart();
			int height = image.getHeight() / logoConfig.getLogoPart();
			// logo起始位置，此目的是为logo居中显示
			int x = (image.getWidth() - width) / 2;
			int y = (image.getHeight() - height) / 2;
			// 绘制图
			g.drawImage(logo, x, y, width, height, null);
			// 给logo画边框
			// 构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
			g.setStroke(new BasicStroke(logoConfig.getBorder()));
			g.setColor(logoConfig.getBorderColor());
			g.drawRect(x, y, width, height);
			g.dispose();
			// 写入logo照片到二维码
			ImageIO.write(image, formate, new File(imagePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
