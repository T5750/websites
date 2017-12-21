package com.evangel.chart.pie;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

public class PieChart3DDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChart3DDemo1(String string) {
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
		JFreeChart jfreechart = ChartFactory.createPieChart3D(
				"Pie Chart 3D Demo 1", piedataset, true, true, false);
		PiePlot3D pieplot3d = (PiePlot3D) jfreechart.getPlot();
		pieplot3d.setStartAngle(180.0);
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		pieplot3d.setForegroundAlpha(0.5F);
		pieplot3d.setNoDataMessage("No data to display");
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		PieChart3DDemo1 pieChart3DDemo1 = new PieChart3DDemo1(
				"Pie Chart 3D Demo 1");
		pieChart3DDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(pieChart3DDemo1);
		pieChart3DDemo1.setVisible(true);
	}
}