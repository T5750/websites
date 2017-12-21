package com.evangel.chart.util;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;

public class ChartUtil {
	public static void encode() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");// 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));// 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));// 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 应用主题样式
	}
}
