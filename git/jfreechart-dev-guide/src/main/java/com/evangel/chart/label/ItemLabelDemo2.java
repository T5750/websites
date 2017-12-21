package com.evangel.chart.label;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
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

public class ItemLabelDemo2 extends ApplicationFrame {
	static class LabelGenerator extends AbstractCategoryItemLabelGenerator
			implements CategoryItemLabelGenerator {
		private Integer category;
		private NumberFormat formatter = NumberFormat.getPercentInstance();

		public LabelGenerator(int i) {
			this(new Integer(i));
		}

		public LabelGenerator(Integer integer) {
			super("", NumberFormat.getInstance());
			category = integer;
		}

		public String generateLabel(CategoryDataset categorydataset, int i,
				int i_0_) {
			String string = null;
			double d = 0.0;
			if (category != null) {
				Number number = categorydataset
						.getValue(i, category.intValue());
				d = number.doubleValue();
			} else
				d = calculateSeriesTotal(categorydataset, i);
			Number number = categorydataset.getValue(i, i_0_);
			if (number != null) {
				double d_1_ = number.doubleValue();
				string = (number.toString() + " (" + formatter.format(d_1_ / d) + ")");
			}
			return string;
		}

		private double calculateSeriesTotal(CategoryDataset categorydataset,
				int i) {
			double d = 0.0;
			for (int i_2_ = 0; i_2_ < categorydataset.getColumnCount(); i_2_++) {
				Number number = categorydataset.getValue(i, i_2_);
				if (number != null)
					d += number.doubleValue();
			}
			return d;
		}
	}

	public ItemLabelDemo2(String string) {
		super(string);
		CategoryDataset categorydataset = createDataset();
		JFreeChart jfreechart = createChart(categorydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartpanel);
	}

	private static CategoryDataset createDataset() {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(100.0, "S1", "C1");
		defaultcategorydataset.addValue(44.3, "S1", "C2");
		defaultcategorydataset.addValue(93.0, "S1", "C3");
		defaultcategorydataset.addValue(80.0, "S2", "C1");
		defaultcategorydataset.addValue(75.1, "S2", "C2");
		defaultcategorydataset.addValue(15.1, "S2", "C3");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart(
				"Item Label Demo 2", "Category", "Value", categorydataset,
				PlotOrientation.HORIZONTAL, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.white);
		categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setUpperMargin(0.25);
		CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
		categoryitemrenderer.setBaseItemLabelsVisible(true);
		categoryitemrenderer.setBaseItemLabelGenerator(new LabelGenerator(
				(Integer) null));
		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String[] strings) {
		ItemLabelDemo2 itemlabeldemo2 = new ItemLabelDemo2("Item Label Demo 2");
		itemlabeldemo2.pack();
		RefineryUtilities.centerFrameOnScreen(itemlabeldemo2);
		itemlabeldemo2.setVisible(true);
	}
}