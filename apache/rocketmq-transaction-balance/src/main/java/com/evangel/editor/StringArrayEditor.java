/**
 * Copyright 2013 TPRI. All Rights Reserved.
 */
package com.evangel.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * <B>系统名称：</B>治超信息系统<BR>
 * <B>模块名称：</B>通用系统功能<BR>
 * <B>中文类名：</B>String[]类型编辑器<BR>
 * <B>概要说明：</B>扩展数据类型编辑的映射功能。<BR>
 * 
 * @author 交通运输部规划研究院（白贺卓）
 * @since 2013-10-23
 */
public class StringArrayEditor extends PropertyEditorSupport {
	/** 切分标记 */
	public static final String SPLIT_FLAG = ",";

	/**
	 * <B>方法名称：</B>获取属性值文本<BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @return String 文本
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public String getAsText() {
		String[] value = (String[]) getValue();
		if (value == null || value.length < 1) {
			return null;
		}
		StringBuffer text = new StringBuffer();
		for (int i = 0; i < value.length; i++) {
			if (i > 0) {
				text.append(SPLIT_FLAG);
			}
			text.append(value[i]);
		}
		return text.toString();
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
		if (StringUtils.isBlank(text)) {
			setValue(null);
			return;
		}
		setValue(text.split(SPLIT_FLAG));
	}
}
