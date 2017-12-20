package com.evangel.chart.bar;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import com.evangel.chart.util.ChartUtil;

public class BarChart3 {
	public static String genBarChart(HttpSession session) throws Exception {
		double[][] data = new double[][] { { 1320 }, { 720 }, { 830 }, { 400 } };
		String[] rowKeys = { "苹果", "香蕉", "橘子", "梨子" };
		String[] columnKeys = { "深圳" };
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data);
		ChartUtil.encode();
		JFreeChart chart = ChartFactory.createBarChart3D("水果销售统计图", "水果", "销售",
				dataset, PlotOrientation.VERTICAL, true, true, true);
		String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500,
				null, session);
		return fileName;
	}
}
