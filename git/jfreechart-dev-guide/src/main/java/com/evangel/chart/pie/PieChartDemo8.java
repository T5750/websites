package com.evangel.chart.pie;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo8 extends ApplicationFrame {
	private static final long serialVersionUID = 2598557557724085474L;

	static class CustomLabelGenerator implements PieSectionLabelGenerator {
		public String generateSectionLabel(PieDataset piedataset,
				Comparable comparable) {
			String string = null;
			if (piedataset != null && !comparable.equals("Two"))
				string = comparable.toString();
			return string;
		}

		public AttributedString generateAttributedSectionLabel(
				PieDataset piedataset, Comparable comparable) {
			Object object = null;
			String string = comparable.toString();
			String string_0_ = (string + " : " + String.valueOf(piedataset
					.getValue(comparable)));
			AttributedString attributedstring = new AttributedString(string_0_);
			attributedstring.addAttribute(TextAttribute.WEIGHT,
					TextAttribute.WEIGHT_BOLD, 0, string.length() - 1);
			return attributedstring;
		}
	}

	public PieChartDemo8(String string) {
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
		JFreeChart jfreechart = ChartFactory.createPieChart("Pie Chart Demo 8",
				piedataset, false, true, false);
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setLabelGenerator(new CustomLabelGenerator());
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		PieChartDemo8 piechartdemo8 = new PieChartDemo8("Pie Chart Demo 8");
		piechartdemo8.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo8);
		piechartdemo8.setVisible(true);
	}
}
