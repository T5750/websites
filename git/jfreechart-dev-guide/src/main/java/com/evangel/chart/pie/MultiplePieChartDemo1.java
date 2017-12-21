package com.evangel.chart.pie;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class MultiplePieChartDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public MultiplePieChartDemo1(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset() {
		double[][] ds = { { 3.0, 4.0, 3.0, 5.0 }, { 5.0, 7.0, 6.0, 8.0 },
				{ 5.0, 7.0, Double.NaN, 3.0 }, { 1.0, 2.0, 3.0, 4.0 },
				{ 2.0, 3.0, 2.0, 3.0 } };
		CategoryDataset categorydataset = DatasetUtilities
				.createCategoryDataset("Region ", "Sales/Q", ds);
		return categorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createMultiplePieChart(
				"Multiple Pie Chart", categorydataset, TableOrder.BY_ROW, true,
				true, false);
		MultiplePiePlot multiplepieplot = (MultiplePiePlot) jfreechart
				.getPlot();
		JFreeChart jfreechart_0_ = multiplepieplot.getPieChart();
		PiePlot pieplot = (PiePlot) jfreechart_0_.getPlot();
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
		pieplot.setLabelFont(new Font("SansSerif", 0, 8));
		pieplot.setInteriorGap(0.3);
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		MultiplePieChartDemo1 multiplePieChartDemo1 = new MultiplePieChartDemo1(
				"Multiple Pie Chart Demo 1");
		multiplePieChartDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(multiplePieChartDemo1);
		multiplePieChartDemo1.setVisible(true);
	}
}