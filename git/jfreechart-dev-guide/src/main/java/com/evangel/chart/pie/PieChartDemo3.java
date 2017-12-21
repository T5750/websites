package com.evangel.chart.pie;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo3 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	public PieChartDemo3(String string) {
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
		JFreeChart jfreechart = ChartFactory.createPieChart("Pie Chart Demo 3",
				null, true, true, false);
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setNoDataMessage("没有有效的数据显示!");
		pieplot.setNoDataMessageFont(new Font("黑体", 2, 20));
		pieplot.setNoDataMessagePaint(Color.red);
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		PieChartDemo3 piechartdemo3 = new PieChartDemo3("Pie Chart Demo 3");
		piechartdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo3);
		piechartdemo3.setVisible(true);
	}
}