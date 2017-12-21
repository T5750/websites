package com.evangel.chart.pie;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo5 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo5(String string) {
		super(string);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel() {
		JPanel jpanel = new JPanel(new GridLayout(2, 2));
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Section 1", 23.3);
		defaultpiedataset.setValue("Section 2", 56.5);
		defaultpiedataset.setValue("Section 3", 43.3);
		defaultpiedataset.setValue("Section 4", 11.1);
		//
		JFreeChart jfreechart = ChartFactory.createPieChart("Chart 1",
				defaultpiedataset, false, false, false);
		jfreechart.addSubtitle(new TextTitle("setCircular(true);", new Font(
				"Dialog", 0, 12)));
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setCircular(true);
		//
		JFreeChart jfreechart_0_ = ChartFactory.createPieChart("Chart 2",
				defaultpiedataset, false, false, false);
		jfreechart_0_.addSubtitle(new TextTitle("setCircular(false);",
				new Font("Dialog", 0, 12)));
		PiePlot pieplot_1_ = (PiePlot) jfreechart_0_.getPlot();
		pieplot_1_.setCircular(false);
		//
		JFreeChart jfreechart_2_ = ChartFactory.createPieChart3D("Chart 3",
				defaultpiedataset, false, false, false);
		jfreechart_2_.addSubtitle(new TextTitle("setCircular(true);", new Font(
				"Dialog", 0, 12)));
		PiePlot3D pieplot3d = (PiePlot3D) jfreechart_2_.getPlot();
		pieplot3d.setForegroundAlpha(0.6F);
		pieplot3d.setCircular(true);
		//
		JFreeChart jfreechart_3_ = ChartFactory.createPieChart3D("Chart 4",
				defaultpiedataset, false, false, false);
		jfreechart_3_.addSubtitle(new TextTitle("setCircular(false);",
				new Font("Dialog", 0, 12)));
		PiePlot3D pieplot3d_4_ = (PiePlot3D) jfreechart_3_.getPlot();
		pieplot3d_4_.setForegroundAlpha(0.6F);
		pieplot3d_4_.setCircular(false);
		jpanel.add(new ChartPanel(jfreechart));
		jpanel.add(new ChartPanel(jfreechart_0_));
		jpanel.add(new ChartPanel(jfreechart_2_));
		jpanel.add(new ChartPanel(jfreechart_3_));
		jpanel.setPreferredSize(new Dimension(800, 600));
		return jpanel;
	}

	public static void main(String[] strings) {
		PieChartDemo5 piechartdemo5 = new PieChartDemo5("Pie Chart Demo 5");
		piechartdemo5.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo5);
		piechartdemo5.setVisible(true);
	}
}
