package com.evangel.chart.pie;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo7 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo7(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Section 1", 23.3);
		defaultpiedataset.setValue("Section 2", 56.5);
		defaultpiedataset.setValue("Section 3", 43.3);
		defaultpiedataset.setValue("Section 4", 11.1);
		return defaultpiedataset;
	}

	public static JPanel createDemoPanel() {
		PieDataset piedataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createPieChart("Pie Chart Demo 7",
				piedataset, false, true, false);
		jfreechart.setBackgroundPaint(new Color(222, 222, 255));
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setBackgroundPaint(Color.white);
		pieplot.setCircular(true);
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat
						.getPercentInstance()));
		pieplot.setNoDataMessage("No data available");
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		// Rotator rotator = new Rotator(pieplot);
		// rotator.start();
		return chartpanel;
	}

	public static void main(String[] strings) {
		PieChartDemo7 piechartdemo7 = new PieChartDemo7("Pie Chart Demo 7");
		piechartdemo7.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo7);
		piechartdemo7.setVisible(true);
	}
}