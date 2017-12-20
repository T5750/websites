package com.evangel.chart.bar;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

import com.evangel.chart.util.ChartUtil;

public class BarChart2 {
	public static String genBarChart(HttpSession session) throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(510, "深圳", "苹果");
		dataset.addValue(320, "深圳", "香蕉");
		dataset.addValue(580, "深圳", "橘子");
		dataset.addValue(390, "深圳", "梨子");
		ChartUtil.encode();
		JFreeChart chart = ChartFactory.createBarChart3D("水果销售统计图", "水果", "销售",
				dataset, PlotOrientation.HORIZONTAL, true, true, true);
		String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500,
				null, session);
		return fileName;
	}
}
