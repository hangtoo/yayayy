
package com.hangtoo.common.chart;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
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

	public static BufferedImage getQrcode(QrcodeEntity qrcodeEntity) {
		BufferedImage image = QrcodeUtils.getQrcodeBufferedImage(qrcodeEntity.getContent(), qrcodeEntity.getCode_width(), null,qrcodeEntity.getFrontColor(),qrcodeEntity.getBgColor());
		return image;
	}

	public static BufferedImage getTextPicAndQrcode(BufferedImage bgImg,List<StringEntity> strs,QrcodeEntity qrcodeEntity,PicEntity picEntity) {
		int imagewidth=bgImg.getWidth();
		int imageheight=bgImg.getHeight();
		//背景图片
		Graphics g =null;
		if(bgImg!=null) {
			g = bgImg.getGraphics();
			imagewidth=bgImg.getWidth();
			imageheight=bgImg.getHeight();
		}else {
			bgImg = new BufferedImage(imagewidth, imageheight, BufferedImage.TYPE_INT_RGB);
			g = bgImg.getGraphics();
			g.setColor(new Color(qrcodeEntity.getBgColor()));
			g.fillRect(0, 0, imagewidth, imageheight);
		}
		
		//二维码
		BufferedImage qrcodeImage=getQrcode(qrcodeEntity);
		g.drawImage(qrcodeImage, qrcodeEntity.getX(), qrcodeEntity.getY(), qrcodeEntity.getCode_width(), qrcodeEntity.getCode_width(), null);
		
		//图片
		g.drawImage(picEntity.getPic(), picEntity.getX(), picEntity.getY(),picEntity.getPic().getWidth(),picEntity.getPic().getHeight(), null);
		
		//内容
		for(StringEntity str:strs) {
			g.setColor(str.getColor());
			g.setFont(str.getFont());
			g.drawString(str.getContent(), str.getX(), str.getY());
		}
		
		g.dispose();
		
		return bgImg;
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
			//优先以传入的长度为准
			x=x+ele.getContent().length()*ele.getFont().getSize()-ele.getAdjustlength();
		}
		
		return ret;
	}

}
