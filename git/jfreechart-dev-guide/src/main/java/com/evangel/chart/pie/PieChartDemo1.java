package com.evangel.chart.pie;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo1(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("One", new Double(43.2));
		defaultpiedataset.setValue("Two", new Double(10.0));
		defaultpiedataset.setValue("Three", new Double(27.5));
		defaultpiedataset.setValue("Four", new Double(17.5));
		defaultpiedataset.setValue("Five", new Double(11.0));
		defaultpiedataset.setValue("Six", new Double(19.4));
		return defaultpiedataset;
	}

	private static JFreeChart createChart(PieDataset piedataset) {
		JFreeChart jfreechart = ChartFactory.createPieChart("Pie Chart Demo",
				piedataset, true, true, false);
		TextTitle texttitle = jfreechart.getTitle();
		texttitle.setToolTipText("A title tooltip!");
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setLabelFont(new Font("Arial Black", 0, 20));
		pieplot.setNoDataMessage("No data available");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02);
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		PieChartDemo1 piechartdemo1 = new PieChartDemo1("Pie Chart Demo 1");
		piechartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo1);
		piechartdemo1.setVisible(true);
	}
}
