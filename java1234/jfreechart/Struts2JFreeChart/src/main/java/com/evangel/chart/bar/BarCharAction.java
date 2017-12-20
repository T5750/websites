package com.evangel.chart.bar;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

import com.evangel.chart.util.ChartUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BarCharAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;

	public JFreeChart getChart() {
		return chart;
	}

	@Override
	public String execute() throws Exception {
		double[][] data = new double[][] { { 1320, 1110, 1123, 321 },
				{ 720, 210, 1423, 1321 }, { 830, 1310, 123, 521 },
				{ 400, 1110, 623, 321 } };
		String[] rowKeys = { "苹果", "香蕉", "橘子", "梨子" };
		String[] columnKeys = { "深圳", "北京", "上海", "南京" };
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data);
		ChartUtil.encode();
		chart = ChartFactory.createBarChart3D("水果销售统计图", "水果", "销售", dataset,
				PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot plot = chart.getCategoryPlot();
		// 设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		// 显示每个柱的数值，并修改该数值的字体属性
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		// 设置平行柱的之间距离
		renderer.setItemMargin(0.4);
		plot.setRenderer(renderer);
		return SUCCESS;
	}
}
