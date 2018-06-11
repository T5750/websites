/**
 * Copyright 2013 TPRI. All Rights Reserved.
 */
package com.evangel.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * <B>系统名称：</B>治超信息系统<BR>
 * <B>模块名称：</B>通用系统功能<BR>
 * <B>中文类名：</B>java.util.Date类型编辑器<BR>
 * <B>概要说明：</B>扩展数据类型编辑的映射功能。<BR>
 * 
 * @author 交通运输部规划研究院（白贺卓）
 * @since 2013-10-23
 */
public class DateEditor extends PropertyEditorSupport {
	/** 默认格式 */
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	/**
	 * <B>方法名称：</B>获取属性值文本<BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return String 文本
	 * @see PropertyEditorSupport#setAsText(String)
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		if (value == null) {
			return null;
		}
		return DateFormatUtils.format(value, DEFAULT_FORMAT);
	}

	/**
	 * <B>方法名称：</B>按照文本设定属性值<BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @param text
	 *            文本
	 * @see PropertyEditorSupport#setAsText(String)
	 */
	@Override
	public void setAsText(String text) {
		if (StringUtils.isBlank(text)) {
			setValue(null);
			return;
		}
		try {
			setValue(DateUtils.parseDate(text, DEFAULT_FORMAT));
		} catch (ParseException e) {
			throw new IllegalArgumentException(text, e);
		}
	}
}
