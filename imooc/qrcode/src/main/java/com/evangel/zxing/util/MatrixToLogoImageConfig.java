package com.evangel.zxing.util;

import java.awt.*;

class MatrixToLogoImageConfig {
	// logo默认边框颜色
	/** Constant <code>DEFAULT_BORDERCOLOR</code> */
	public static final Color DEFAULT_BORDERCOLOR = Color.RED;
	// logo默认边框宽度
	/** Constant <code>DEFAULT_BORDER=2</code> */
	public static final int DEFAULT_BORDER = 2;
	// logo大小默认为照片的1/5
	/** Constant <code>DEFAULT_LOGOPART=5</code> */
	public static final int DEFAULT_LOGOPART = 5;
	private final int border = DEFAULT_BORDER;
	private final Color borderColor;
	private final int logoPart;

	/**
	 * Creates a default config with on color {@link #BLACK} and off color
	 * {@link #WHITE}, generating normal black-on-white barcodes.
	 *
	 * @since 0.0.7
	 */
	public MatrixToLogoImageConfig() {
		this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
	}

	/**
	 * <p>
	 * Constructor for MatrixToLogoImageConfig.
	 * </p>
	 *
	 * @param borderColor
	 *            a {@link java.awt.Color} object.
	 * @param logoPart
	 *            a int.
	 * @since 0.0.7
	 */
	public MatrixToLogoImageConfig(Color borderColor, int logoPart) {
		this.borderColor = borderColor;
		this.logoPart = logoPart;
	}

	/**
	 * <p>
	 * Getter for the field <code>borderColor</code>.
	 * </p>
	 *
	 * @return a {@link java.awt.Color} object.
	 * @since 0.0.7
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * <p>
	 * Getter for the field <code>border</code>.
	 * </p>
	 *
	 * @return a int.
	 * @since 0.0.7
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * <p>
	 * Getter for the field <code>logoPart</code>.
	 * </p>
	 *
	 * @return a int.
	 * @since 0.0.7
	 */
	public int getLogoPart() {
		return logoPart;
	}
}