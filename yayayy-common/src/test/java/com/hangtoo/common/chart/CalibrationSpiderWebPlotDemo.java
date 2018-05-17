package com.hangtoo.common.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 * 刻度蜘蛛环图示例
 * 
 * @author 成尚謙 lhx222@gmail.com
 * @since 2011-09-14
 * @version 1.0
 */
public class CalibrationSpiderWebPlotDemo extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private static String content = "http://dblink.sunfintech.com.cn/mobile";
	/**
	 * 创建DEMO实例
	 * 
	 * @param s
	 */
	public CalibrationSpiderWebPlotDemo(String s) {
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(300, 600));
		setContentPane(jpanel);
	}

	/**
	 * 构造数据集
	 * 
	 * @return
	 */
	private static CategoryDataset createDataset() {
		String group1 = "学科能力 ";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(28, group1, "语言知识应用");
		dataset.addValue(70, group1, "口头表达");
		dataset.addValue(23, group1, "口头AA");
		dataset.addValue(45, group1, "口头AB");
		dataset.addValue(72, group1, "AA表达");
		dataset.addValue(85, group1, "BB表达");
		//dataset.addValue(40, group1, "书面表达");
		//dataset.addValue(60, group1, "阅读理解");
		//dataset.addValue(42, group1, "书面22表达");
		//dataset.addValue(64, group1, "阅读33理解");

		return dataset;
	}

	/**
	 * 创建图表
	 * 
	 * @param categorydataset
	 * @return
	 */
	private static JFreeChart createChart(CategoryDataset categorydataset) {
		CalibrationSpiderWebPlot spiderwebplot = new CalibrationSpiderWebPlot(categorydataset);
		// spiderwebplot.setStartAngle(54D);//刻度轴方向:54度,默认是90度即垂直于水平面
		spiderwebplot.setInteriorGap(0.40000000000000002D);
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());// 标准分类提示器

		// 是否画环
		 //spiderwebplot.setDrawRing(false);

		// 刻度最大值
		// spiderwebplot.setMaxValue(500);

		// 刻度数
		// spiderwebplot.setTicks(5);

		// 刻度前缀
		// spiderwebplot.setLablePrefix("%");

		// 刻度后缀
		spiderwebplot.setLableSuffix("%");
		// 饼图的背景全透明  
		spiderwebplot.setBackgroundAlpha(1.0f);
		//JFreeChart jfreechart = new JFreeChart("学科能力11111", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		JFreeChart jfreechart = new JFreeChart(spiderwebplot);
		try {
			jfreechart.setBorderVisible(false);  
			jfreechart.setBackgroundPaint(null);  
			jfreechart.setBackgroundImageAlpha(1f);  
			
			LegendTitle legendtitle = new LegendTitle(spiderwebplot);
			legendtitle.setPosition(RectangleEdge.BOTTOM);
			//jfreechart.addSubtitle(legendtitle);
			jfreechart.removeLegend();
			//jfreechart.addLegend(legendtitle);
			
			
			File orcode=QrcodeUtils.createQrcodeFile(content, 150, null);
			BufferedImage image = ImageIO.read(orcode);
			
			BufferedImage ret=new BufferedImage(300,600,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g=ret.createGraphics();
			g.fillRect(0, 0, 300, 600);
			//Graphics g=ret.getGraphics();
			g.setColor(Color.ORANGE);
			//g.setBackground(Color.ORANGE); 

			g.drawImage(image,0,300,150,150,null);
			
			BufferedImage chartimage=jfreechart.createBufferedImage(250,250);
			g.drawImage(chartimage,0,0,250,250,null);
			
			g.drawString("welcome", 0, 450);
			
			g.dispose();

			
			//ChartUtilities.writeChartAsPNG(out, jfreechart, 300, 600);
			// 应用标准的主题外观
			//ChartUtilities.applyCurrentTheme(jfreechart);
			
			FileOutputStream out=new FileOutputStream("/Users/hlf/Desktop/1.png");
			ChartUtilities.writeBufferedImageAsPNG(out, ret);
			//createImage("/Users/hlf/Desktop/2.jpg",ret);
			
			return jfreechart;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static void createImage(String fileLocation, BufferedImage image) {  
        try {  
            FileOutputStream fos = new FileOutputStream(fileLocation);  
            BufferedOutputStream bos = new BufferedOutputStream(fos);  
            com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(bos);  
            encoder.encode(image);  
            bos.close();  
            fos.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	/**
	 * 创建面板
	 * 
	 * @return
	 */
	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		CalibrationSpiderWebPlotDemo calibrationSpiderWebPlotDemo = new CalibrationSpiderWebPlotDemo(
				"JFreeChart: CalibrationSpiderWebPlotDemo.java");
		calibrationSpiderWebPlotDemo.pack();
		RefineryUtilities.centerFrameOnScreen(calibrationSpiderWebPlotDemo);
		calibrationSpiderWebPlotDemo.setVisible(true);
	}
}
