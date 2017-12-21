package com.evangel.chart.line;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class LineChartDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = -6354350604313079793L;
	/* synthetic */
	static Class class$demo$LineChartDemo1;

	public LineChartDemo1(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset() {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(212.0, "Classes", "JDK 1.0");
		defaultcategorydataset.addValue(504.0, "Classes", "JDK 1.1");
		defaultcategorydataset.addValue(1520.0, "Classes", "JDK 1.2");
		defaultcategorydataset.addValue(1842.0, "Classes", "JDK 1.3");
		defaultcategorydataset.addValue(2991.0, "Classes", "JDK 1.4");
		defaultcategorydataset.addValue(3500.0, "Classes", "JDK 1.5");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createLineChart(
				"Java Standard Class Library",// 图表标题
				null, // 主轴标签
				"Class Count",// 范围轴标签
				categorydataset, // 数据集
				PlotOrientation.VERTICAL,// 方向
				false, // 是否包含图例
				true, // 提示信息是否显示
				false// 是否使用urls
				);
		// 添加主标题
		jfreechart.addSubtitle(new TextTitle("Number of Classes By Release"));
		TextTitle texttitle = (new TextTitle(
				"Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)"));
		texttitle.setFont(new Font("SansSerif", 0, 10));
		texttitle.setPosition(RectangleEdge.BOTTOM);
		texttitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		jfreechart.addSubtitle(texttitle);
		// 改变图表的背景颜色
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setRangeGridlinePaint(Color.white);
		categoryplot.setRangeGridlinesVisible(false);
		URL url = (class$demo$LineChartDemo1 == null ? class$demo$LineChartDemo1 = class$("com.evangel.chart.line.LineChartDemo1")
				: class$demo$LineChartDemo1).getClassLoader().getResource(
				"OnBridge11small.png");
		if (url != null) {
			ImageIcon imageicon = new ImageIcon(url);
			jfreechart.setBackgroundImage(imageicon.getImage());
			categoryplot.setBackgroundPaint(new Color(0, 0, 0, 0));
		}
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();
		lineandshaperenderer.setShapesVisible(true);
		lineandshaperenderer.setDrawOutlines(true);
		lineandshaperenderer.setUseFillPaint(true);
		lineandshaperenderer.setBaseFillPaint(Color.white);
		lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));
		lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
		lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0,
				10.0, 10.0));
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		LineChartDemo1 linechartdemo1 = new LineChartDemo1(
				"JFreeChart - Line Chart Demo 1");
		linechartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo1);
		linechartdemo1.setVisible(true);
	}

	/* synthetic */
	static Class class$(String string) {
		Class var_class;
		try {
			var_class = Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
		return var_class;
	}
}