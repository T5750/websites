package com.evangel.chart.label;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ItemLabelDemo1 extends ApplicationFrame {
	static class LabelGenerator extends AbstractCategoryItemLabelGenerator
			implements CategoryItemLabelGenerator {
		private double threshold;

		public LabelGenerator(double d) {
			super("", NumberFormat.getInstance());
			threshold = d;
		}

		public String generateLabel(CategoryDataset categorydataset, int i,
				int i_0_) {
			String string = null;
			Number number = categorydataset.getValue(i, i_0_);
			if (number != null) {
				double d = number.doubleValue();
				if (d > threshold)
					string = number.toString();
			}
			return string;
		}
	}

	public ItemLabelDemo1(String string) {
		super(string);
		CategoryDataset categorydataset = createDataset();
		JFreeChart jfreechart = createChart(categorydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartpanel);
	}

	private static CategoryDataset createDataset() {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(11.0, "S1", "C1");
		defaultcategorydataset.addValue(44.3, "S1", "C2");
		defaultcategorydataset.addValue(93.0, "S1", "C3");
		defaultcategorydataset.addValue(35.6, "S1", "C4");
		defaultcategorydataset.addValue(75.1, "S1", "C5");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart(
				"Item Label Demo 1", "Category", "Value", categorydataset,
				PlotOrientation.VERTICAL, false, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.white);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setUpperMargin(0.15);
		CategoryItemRenderer renderer = categoryplot.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new LabelGenerator(75));
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		ItemLabelDemo1 itemlabeldemo1 = new ItemLabelDemo1("Item Label Demo 1");
		itemlabeldemo1.pack();
		RefineryUtilities.centerFrameOnScreen(itemlabeldemo1);
		itemlabeldemo1.setVisible(true);
	}
}