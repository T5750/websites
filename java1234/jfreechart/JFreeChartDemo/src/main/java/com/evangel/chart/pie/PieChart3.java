package com.evangel.chart.pie;

import java.awt.*;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.evangel.chart.util.ChartUtil;

public class PieChart3 {
	public static String getPieChart(HttpSession session) throws Exception {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("黑心矿难", 1000);
		dataset.setValue("醉酒驾驶", 800);
		dataset.setValue("城管强拆", 400);
		dataset.setValue("医疗事故", 100);
		dataset.setValue("其他", 29);
		ChartUtil.encode();
		JFreeChart chart = ChartFactory.createPieChart3D("非正常死亡人数分布图", dataset,
				true, true, true);
		// 副标题
		chart.addSubtitle(new TextTitle("2013年度"));
		PiePlot pieplot = (PiePlot) chart.getPlot();
		pieplot.setLabelFont(new Font("宋体", 0, 11));
		// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
		pieplot.setCircular(true);
		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator(
				"{0}:({1}.{2})", NumberFormat.getNumberInstance(),
				NumberFormat.getPercentInstance());
		pieplot.setLabelGenerator(standarPieIG);
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();
		// 设置开始角度
		pieplot3d.setStartAngle(120D);
		// 设置方向为”顺时针方向“
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		// 设置透明度，0.5F为半透明，1为不透明，0为全透明
		pieplot3d.setForegroundAlpha(0.7F);
		String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500,
				null, session);
		return fileName;
	}
}
