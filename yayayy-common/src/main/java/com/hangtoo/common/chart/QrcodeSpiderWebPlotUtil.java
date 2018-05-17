
package com.hangtoo.common.chart;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class QrcodeSpiderWebPlotUtil {
	
	/**
	 * 背景相关
	 * @param bg
	 * @param imagewidth
	 * @param imageheight
	 * 雷达图
	 * @param dataset
	 * 二维码
	 * @param codeparam
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getQrcodeSpiderWebPlot(BufferedImage bgImg,int imagewidth, int imageheight,CategoryDataset dataset,QrcodeEntity codeparam,List<StringEntity> strs) throws IOException {
		
		//背景图片
		Graphics g =null;
		if(bgImg!=null) {
			g = bgImg.getGraphics();
			
			imagewidth=bgImg.getWidth();
			imageheight=bgImg.getHeight();
			
		}else {
			bgImg = new BufferedImage(imagewidth, imageheight, BufferedImage.TYPE_INT_RGB);
			g = bgImg.getGraphics();
			g.setColor(new Color(codeparam.getBgColor()));
			g.fillRect(0, 0, imagewidth, imageheight);
		}

		int code_width=Double.valueOf(imagewidth*0.28).intValue();

		int chart_width=Double.valueOf(imagewidth*0.75).intValue();
		int chart_height=Double.valueOf(chart_width*0.8).intValue();
		
		int code_y=imageheight/3*2+20;

		//雷达图
		BufferedImage spiderWebPlotImage=getSpiderWebPlot(dataset,chart_width,chart_height,4);
		g.drawImage(spiderWebPlotImage, (imagewidth - chart_width) / 2, imageheight/4, chart_width, chart_height, null);

		//二维码
		codeparam.setCode_width(code_width);
		BufferedImage qrcodeImage=getQrcode(codeparam);
		g.drawImage(qrcodeImage, (imagewidth - code_width) / 2, code_y, code_width, code_width, null);

		//内容
		for(StringEntity str:strs) {
			g.setColor(str.getColor());
			g.setFont(str.getFont());
			g.drawString(str.getContent(), str.getX(), str.getY());
		}

		g.dispose();
		
		return bgImg;
	}

	public static BufferedImage getQrcode(QrcodeEntity codeparam) {
		BufferedImage image = QrcodeUtils.getQrcodeBufferedImage(codeparam.getContent(), codeparam.getCode_width(), null,codeparam.getFrontColor(),codeparam.getBgColor());
		return image;
	}

	public static BufferedImage getSpiderWebPlot(CategoryDataset dataset,int chart_width, int chart_height,int ticks) {
		
		CalibrationSpiderWebPlot spiderwebplot = new CalibrationSpiderWebPlot(dataset);
		// spiderwebplot.setStartAngle(54D);//刻度轴方向:54度,默认是90度即垂直于水平面
		spiderwebplot.setInteriorGap(0.40000000000000002D);
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());// 标准分类提示器
		spiderwebplot.setTicks(ticks);
		// 刻度后缀
		//spiderwebplot.setLableSuffix("%");
		spiderwebplot.setDrawLable(false);
		
		spiderwebplot.setRingColor(Color.YELLOW);
		spiderwebplot.setAxisLinePaint(Color.YELLOW);
		
		//各维度的标题的颜色
		spiderwebplot.setLabelFont(new Font(null,0,32));
		spiderwebplot.setLabelPaint(Color.WHITE);
		spiderwebplot.setSeriesPaint(Color.YELLOW);

		// 饼图的背景全透明
		spiderwebplot.setBackgroundAlpha(0f);
		spiderwebplot.setOutlineVisible(false);
		JFreeChart jfreechart = new JFreeChart(spiderwebplot);

		jfreechart.setBorderVisible(false);
		jfreechart.setBackgroundPaint(null);
		jfreechart.setBackgroundImageAlpha(0f);

		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.removeLegend();
		
		BufferedImage chartimage = jfreechart.createBufferedImage(chart_width, chart_height);
		return chartimage;
	}
	
	public static List<StringEntity> getStringLine(StringLine line) {
		
		List<StringEntity> ret=new ArrayList<>();
		StringEntity e;
		int x=line.getX();
		int y=line.getY();
		for(StringElement ele:line.getList()) {
			e=new StringEntity();
			e.setContent(ele.getContent());
			e.setX(x);
			e.setY(y);
			e.setColor(ele.getColor());
			e.setFont(ele.getFont());
			ret.add(e);
			x=x+ele.getContent().length()*ele.getFont().getSize();
		}
		
		return ret;
	}
	

	public static void main(String args[]) throws IOException {
		
		long start=System.currentTimeMillis();
		
		int YELLOW = 0xFF743018;
		int NOCOLOR = 0x000000;
		QrcodeEntity codeparam=new QrcodeEntity();
		codeparam.setContent("http://wx.sunfintech.com.cn/mobile");
		codeparam.setBgColor(NOCOLOR);
		codeparam.setFrontColor(YELLOW);
		
		int x0=30;
		int y0=220;
		int y1=270;
		
		///背景
		File bg=new File("/Users/hlf/Desktop/bg.png");
		BufferedImage bgImg=null;
		if(bg!=null&&bg.exists()) {
			bgImg = ImageIO.read(bg);
		}

		
		//////////标题
		StringLine line=new StringLine();
		List<StringElement> list=new ArrayList<>();

		StringElement e=new StringElement("真理之口财富预言",Color.BLACK,new Font(null,0,28));
		list.add(e);
		
		line.setList(list);
		line.setX((bgImg.getWidth()-e.getContent().length()*e.getFont().getSize())/2);
		line.setY(50);
		List<StringEntity> strs=getStringLine(line);
		
		//////////第一行
		line=new StringLine();
		list=new ArrayList<>();

		e=new StringElement("为师掐指一算，神创造你的时候将你设定为",Color.WHITE,new Font(null,0,30));
		list.add(e);
		
		line.setList(list);
		line.setX(x0);
		line.setY(y0);
		strs.addAll(getStringLine(line));
		
		///////////第二行
		line=new StringLine();
		list=new ArrayList<>();

		e=new StringElement("中产阶级",Color.YELLOW,new Font(null,0,50));
		list.add(e);
		e=new StringElement("，你将拥有超过",Color.WHITE,new Font(null,0,30));
		list.add(e);
		e=new StringElement("千万",Color.YELLOW,new Font(null,0,50));
		list.add(e);
		e=new StringElement("的资产",Color.WHITE,new Font(null,0,30));
		list.add(e);
		
		line.setList(list);
		line.setX(x0);
		line.setY(y1);
		strs.addAll(getStringLine(line));
		//////////最后一行
		line=new StringLine();
		list=new ArrayList<>();

		e=new StringElement("长按二维码玩游戏测一下你的财富之路",Color.WHITE,new Font(null,0,30));
		list.add(e);
		
		line.setList(list);
		line.setX((bgImg.getWidth()-e.getContent().length()*e.getFont().getSize())/2);
		line.setY(bgImg.getHeight()-150);
		strs.addAll(getStringLine(line));
		///////////
		
		
		String group1 = "财富预言";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(28, group1, "意外之财");
		dataset.addValue(70, group1, "坐享其成");
		dataset.addValue(40, group1, "贵人云集");
		dataset.addValue(60, group1, "生财有道");
		dataset.addValue(80, group1, "辛勤耕耘");
		
		
		BufferedImage ret=getQrcodeSpiderWebPlot(bgImg,0,0,dataset,codeparam,strs);
		FileOutputStream out = new FileOutputStream("/Users/hlf/Desktop/QrcodeSpiderWebPlot.png");
		ChartUtilities.writeBufferedImageAsPNG(out, ret);
		
		ret=getSpiderWebPlot(dataset,500,500,5);
		out = new FileOutputStream("/Users/hlf/Desktop/SpiderWebPlot.png");
		ChartUtilities.writeBufferedImageAsPNG(out, ret);
		
		ret=getQrcode(codeparam);
		out = new FileOutputStream("/Users/hlf/Desktop/Qrcode.png");
		ChartUtilities.writeBufferedImageAsPNG(out, ret);
		
		System.out.println(System.currentTimeMillis()-start);
	}
}
