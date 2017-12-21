package com.evangel.chart.bar;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	public BarChartDemo1(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset() {
		String string = "First";
		String string_0_ = "Second";
		String string_1_ = "Third";
		String string_2_ = "Category 1";
		String string_3_ = "Category 2";
		String string_4_ = "Category 3";
		String string_5_ = "Category 4";
		String string_6_ = "Category 5";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0, string, string_2_);
		defaultcategorydataset.addValue(4.0, string, string_3_);
		defaultcategorydataset.addValue(3.0, string, string_4_);
		defaultcategorydataset.addValue(5.0, string, string_5_);
		defaultcategorydataset.addValue(5.0, string, string_6_);
		defaultcategorydataset.addValue(5.0, string_0_, string_2_);
		defaultcategorydataset.addValue(7.0, string_0_, string_3_);
		defaultcategorydataset.addValue(6.0, string_0_, string_4_);
		defaultcategorydataset.addValue(8.0, string_0_, string_5_);
		defaultcategorydataset.addValue(4.0, string_0_, string_6_);
		defaultcategorydataset.addValue(4.0, string_1_, string_2_);
		defaultcategorydataset.addValue(3.0, string_1_, string_3_);
		defaultcategorydataset.addValue(2.0, string_1_, string_4_);
		defaultcategorydataset.addValue(3.0, string_1_, string_5_);
		defaultcategorydataset.addValue(6.0, string_1_, string_6_);
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 1",
				"Category", "Value", categorydataset, PlotOrientation.VERTICAL,
				true, true, false);
		jfreechart.setBackgroundPaint(Color.WHITE);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeGridlinePaint(Color.white);
		// 刻度轴刻度设置
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// renderer设置
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);// 设置外廓线不可见
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue,
				0.0F, 0.0F, new Color(0, 0, 64));
		GradientPaint gradientpaint_7_ = new GradientPaint(0.0F, 0.0F,
				Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		GradientPaint gradientpaint_8_ = new GradientPaint(0.0F, 0.0F,
				Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, gradientpaint_7_);
		barrenderer.setSeriesPaint(2, gradientpaint_8_);
		// 设置种类标签旋转的角度，逆时针旋转
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6));
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		BarChartDemo1 barchartdemo1 = new BarChartDemo1("Bar Chart Demo");
		barchartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo1);
		barchartdemo1.setVisible(true);
	}
}