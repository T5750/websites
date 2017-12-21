package com.evangel.chart.line;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.*;

public class LineChartDemo2 extends ApplicationFrame {
	private static final long serialVersionUID = -6354350604313079793L;

	public LineChartDemo2(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset() {
		XYSeries xyseries = new XYSeries("First");
		xyseries.add(1.0, 1.0);
		xyseries.add(2.0, 4.0);
		xyseries.add(3.0, 3.0);
		xyseries.add(4.0, 5.0);
		xyseries.add(5.0, 5.0);
		xyseries.add(6.0, 7.0);
		xyseries.add(7.0, 7.0);
		xyseries.add(8.0, 8.0);
		XYSeries xyseries_0_ = new XYSeries("Second");
		xyseries_0_.add(1.0, 5.0);
		xyseries_0_.add(2.0, 7.0);
		xyseries_0_.add(3.0, 6.0);
		xyseries_0_.add(4.0, 8.0);
		xyseries_0_.add(5.0, 4.0);
		xyseries_0_.add(6.0, 4.0);
		xyseries_0_.add(7.0, 2.0);
		xyseries_0_.add(8.0, 1.0);
		XYSeries xyseries_1_ = new XYSeries("Third");
		xyseries_1_.add(3.0, 4.0);
		xyseries_1_.add(4.0, 3.0);
		xyseries_1_.add(5.0, 2.0);
		xyseries_1_.add(6.0, 3.0);
		xyseries_1_.add(7.0, 6.0);
		xyseries_1_.add(8.0, 3.0);
		xyseries_1_.add(9.0, 4.0);
		xyseries_1_.add(10.0, 3.0);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries_0_);
		xyseriescollection.addSeries(xyseries_1_);
		return xyseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset) {
		JFreeChart jfreechart = ChartFactory.createXYLineChart(
				"Line Chart Demo 2", // chart title
				"X", // x axis label
				"Y", // y axis label
				xydataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		TextTitle texttitle = (new TextTitle(""));
		texttitle.setFont(new Font("SansSerif", 0, 10));
		texttitle.setPosition(RectangleEdge.BOTTOM);
		texttitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		jfreechart.addSubtitle(texttitle);
		// 改变图表的背景颜色
		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xylineandshaperenderer.setShapesVisible(true);
		xylineandshaperenderer.setShapesFilled(true);
		NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		LineChartDemo2 linechartdemo2 = new LineChartDemo2(
				"JFreeChart - Line Chart Demo 2");
		linechartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo2);
		linechartdemo2.setVisible(true);
	}
}