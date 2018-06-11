/**
 * Copyright 2013 TPRI. All Rights Reserved.
 */
package com.evangel.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * <B>系统名称：</B>治超信息系统<BR>
 * <B>模块名称：</B>通用系统功能<BR>
 * <B>中文类名：</B>java.lang.Integer类型编辑器<BR>
 * <B>概要说明：</B>扩展数据类型编辑的映射功能。<BR>
 * 
 * @author 交通运输部规划研究院（白贺卓）
 * @since 2013-10-23
 */
public class IntegerEditor extends PropertyEditorSupport {
	/**
	 * <B>方法名称：</B>获取属性值文本<BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return String 文本
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public String getAsText() {
		Integer value = (Integer) getValue();
		if (value == null) {
			return null;
		}
		return "" + value;
	}

	/**
	 * <B>方法名称：</B>按照文本设定属性值<BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @param text
	 *            文本
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) {
		Integer value = null;
		String temp = text;
		if (text.startsWith("-")) {
			temp = text.substring(1);
		}
		if (NumberUtils.isDigits(temp)) {
			value = Integer.parseInt(text);
		}
		setValue(value);
	}
}
