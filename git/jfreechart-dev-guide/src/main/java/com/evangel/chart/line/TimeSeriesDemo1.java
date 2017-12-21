package com.evangel.chart.line;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import com.evangel.chart.util.ChartUtil;

public class TimeSeriesDemo1 extends ApplicationFrame {
	private static final long serialVersionUID = -5412286370956646368L;
	/* synthetic */
	static Class class$org$jfree$data$time$Month;

	public TimeSeriesDemo1(String string) {
		super(string);
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = createChart(xydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart, false);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true, false);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset) {
		ChartUtil.encode();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
				"Legal & General Unit Trust Prices", // title
				"Date", // x-axis label
				"Price Per Unit", // y-axis label
				xydataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);
		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		xyplot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot
				.getRenderer();
		if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
			xylineandshaperenderer.setBaseShapesVisible(true);
			xylineandshaperenderer.setBaseShapesFilled(true);
		}
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		return jfreechart;
	}

	private static XYDataset createDataset() {
		TimeSeries timeseries = new TimeSeries(
				"L&G European Index Trust",
				(class$org$jfree$data$time$Month == null ? (class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month"))
						: class$org$jfree$data$time$Month));
		timeseries.add(new Month(2, 2001), 181.8);
		timeseries.add(new Month(3, 2001), 167.3);
		timeseries.add(new Month(4, 2001), 153.8);
		timeseries.add(new Month(5, 2001), 167.6);
		timeseries.add(new Month(6, 2001), 158.8);
		timeseries.add(new Month(7, 2001), 148.3);
		timeseries.add(new Month(8, 2001), 153.9);
		timeseries.add(new Month(9, 2001), 142.7);
		timeseries.add(new Month(10, 2001), 123.2);
		timeseries.add(new Month(11, 2001), 131.8);
		timeseries.add(new Month(12, 2001), 139.6);
		timeseries.add(new Month(1, 2002), 142.9);
		timeseries.add(new Month(2, 2002), 138.7);
		timeseries.add(new Month(3, 2002), 137.3);
		timeseries.add(new Month(4, 2002), 143.9);
		timeseries.add(new Month(5, 2002), 139.8);
		timeseries.add(new Month(6, 2002), 137.0);
		timeseries.add(new Month(7, 2002), 132.8);
		TimeSeries timeseries_0_ = new TimeSeries(
				"L&G UK Index Trust",
				(class$org$jfree$data$time$Month == null ? (class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month"))
						: class$org$jfree$data$time$Month));
		timeseries_0_.add(new Month(2, 2001), 129.6);
		timeseries_0_.add(new Month(3, 2001), 123.2);
		timeseries_0_.add(new Month(4, 2001), 117.2);
		timeseries_0_.add(new Month(5, 2001), 124.1);
		timeseries_0_.add(new Month(6, 2001), 122.6);
		timeseries_0_.add(new Month(7, 2001), 119.2);
		timeseries_0_.add(new Month(8, 2001), 116.5);
		timeseries_0_.add(new Month(9, 2001), 112.7);
		timeseries_0_.add(new Month(10, 2001), 101.5);
		timeseries_0_.add(new Month(11, 2001), 106.1);
		timeseries_0_.add(new Month(12, 2001), 110.3);
		timeseries_0_.add(new Month(1, 2002), 111.7);
		timeseries_0_.add(new Month(2, 2002), 111.0);
		timeseries_0_.add(new Month(3, 2002), 109.6);
		timeseries_0_.add(new Month(4, 2002), 113.2);
		timeseries_0_.add(new Month(5, 2002), 111.6);
		timeseries_0_.add(new Month(6, 2002), 108.8);
		timeseries_0_.add(new Month(7, 2002), 101.6);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries_0_);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main$(String[] strings) {
		TimeSeriesDemo1 timeseriesdemo1 = new TimeSeriesDemo1(
				"Time Series Demo 1");
		timeseriesdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(timeseriesdemo1);
		timeseriesdemo1.setVisible(true);
	}

	public static void main(String[] args) {
		main$(args);
	}

	/* synthetic */
	static Class class$(String string) {
		Class var_class;
		try {
			var_class = Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
		return var_class;
	}
}