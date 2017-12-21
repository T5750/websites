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

public class PieChartDemo6 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo6(String string) {
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

	private static JFreeChart createChart(String title, PieDataset piedataset) {
		JFreeChart jfreechart = ChartFactory.createPieChart(title, piedataset,
				true, true, false);
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
		JPanel jpanel = new JPanel(new GridLayout(2, 2));
		JFreeChart jfreechart = createChart("Pie Chart 1", createDataset());
		Font font = new Font("Dialog", 0, 12);
		jfreechart.addSubtitle(new TextTitle(
				"Ignore nulls: false; Ignore zeros: false;", font));
		JFreeChart jfreechart_0_ = createChart("Pie Chart 2", createDataset());
		jfreechart_0_.addSubtitle(new TextTitle(
				"Ignore nulls: true; Ignore zeros: false;", font));
		PiePlot pieplot = (PiePlot) jfreechart_0_.getPlot();
		pieplot.setIgnoreNullValues(true);
		pieplot.setIgnoreZeroValues(false);
		JFreeChart jfreechart_1_ = createChart("Pie Chart 3", createDataset());
		jfreechart_1_.addSubtitle(new TextTitle(
				"Ignore nulls: false; Ignore zeros: true;", font));
		PiePlot pieplot_2_ = (PiePlot) jfreechart_1_.getPlot();
		pieplot_2_.setIgnoreNullValues(false);
		pieplot_2_.setIgnoreZeroValues(true);
		JFreeChart jfreechart_3_ = createChart("Pie Chart 4", createDataset());
		jfreechart_3_.addSubtitle(new TextTitle(
				"Ignore nulls: true; Ignore zeros: true;", font));
		PiePlot pieplot_4_ = (PiePlot) jfreechart_3_.getPlot();
		pieplot_4_.setIgnoreNullValues(true);
		pieplot_4_.setIgnoreZeroValues(true);
		jpanel.add(new ChartPanel(jfreechart));
		jpanel.add(new ChartPanel(jfreechart_0_));
		jpanel.add(new ChartPanel(jfreechart_1_));
		jpanel.add(new ChartPanel(jfreechart_3_));
		return jpanel;
	}

	public static void main(String[] strings) {
		PieChartDemo6 piechartdemo6 = new PieChartDemo6("Pie Chart Demo 6");
		piechartdemo6.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo6);
		piechartdemo6.setVisible(true);
	}
}