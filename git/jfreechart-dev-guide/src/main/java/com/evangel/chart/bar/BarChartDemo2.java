package com.evangel.chart.bar;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo2 extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	public BarChartDemo2(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	private static CategoryDataset createDataset() {
		double[][] ds = { { 1.0, 43.0, 35.0, 58.0, 54.0, 77.0, 71.0, 89.0 },
				{ 54.0, 75.0, 63.0, 83.0, 43.0, 46.0, 27.0, 13.0 },
				{ 41.0, 33.0, 22.0, 34.0, 62.0, 32.0, 42.0, 34.0 } };
		return DatasetUtilities.createCategoryDataset("Series ", "Factor ", ds);
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 2",
				"Category", "Score (%)", categorydataset,
				PlotOrientation.HORIZONTAL, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setRangeGridlinePaint(Color.white);
		categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setRange(0.0, 100.0);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		barrenderer
				.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator(
						"Tooltip: {0}"));
		return jfreechart;
	}

	public static void main(String[] strings) {
		BarChartDemo2 barchartdemo2 = new BarChartDemo2("Bar Chart Demo");
		barchartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo2);
		barchartdemo2.setVisible(true);
	}
}
