package com.hangtoo.common.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartUtilities;

public class ChartUtil {
	
	public static BufferedImage getTextPicAndQrcode(String content,String customname,String num,String unit,String picpng) throws IOException{ 
		
		InputStream inputStream=QrcodeSpiderWebPlotUtil.class.getResourceAsStream("/resource/chart/bg.png");
		BufferedImage bgImg = ImageIO.read(inputStream);
		
		Color FONTCOLOR=new Color(0xEBD9A0);
		Color YELLOWCOLOR=new Color(0xFFE71A);
		Font NORMALFONT=new Font(null,Font.BOLD,24);
		Font BIGFONT=new Font(null,Font.PLAIN,48);
		Font BIGERFONT=new Font(null,Font.PLAIN,60);
		//Font MOSTBIGERFONT=new Font("MicrosoftYaHei Regular",Font.PLAIN,65);
		//Font MOSTBIGERFONT=new Font("微软雅黑",Font.PLAIN,65);
		Font MOSTBIGERFONT=new Font(null,Font.PLAIN,65);
		//第一行
		StringLine line=new StringLine();
		List<StringElement> list=new ArrayList<>();

		StringElement e=new StringElement("你好",FONTCOLOR,NORMALFONT);
		list.add(e);
		e=new StringElement(customname,FONTCOLOR,BIGFONT);
		list.add(e);
		
		line.setList(list);
		line.setX(60);
		line.setY(305);
		List<StringEntity> strs=QrcodeSpiderWebPlotUtil.getStringLine(line);
		
		//////////第二行
		line=new StringLine();
		list=new ArrayList<>();

		e=new StringElement("10年后，你将拥有",FONTCOLOR,NORMALFONT);
		list.add(e);
		
		e=new StringElement(num,YELLOWCOLOR,MOSTBIGERFONT);
		e.setAdjustlength(getAdjustLength(num));
		list.add(e);

		e=new StringElement("万",YELLOWCOLOR,MOSTBIGERFONT);
		list.add(e);
		
		e=new StringElement(unit,FONTCOLOR,NORMALFONT);
		list.add(e);
		
		line.setList(list);
		line.setX(60);
		line.setY(377);
		strs.addAll(QrcodeSpiderWebPlotUtil.getStringLine(line));
		
		//////////第三行
		line=new StringLine();
		list=new ArrayList<>();

		e=new StringElement("你命中的财富之词是：",FONTCOLOR,NORMALFONT);
		list.add(e);
		e=new StringElement("坐享其成",YELLOWCOLOR,BIGERFONT);
		list.add(e);
		
		line.setList(list);
		line.setX(60);
		line.setY(472);
		strs.addAll(QrcodeSpiderWebPlotUtil.getStringLine(line));
		//////////
		
		InputStream picinputStream=QrcodeSpiderWebPlotUtil.class.getResourceAsStream("/resource/chart/"+picpng);
		BufferedImage pic = ImageIO.read(picinputStream);
		PicEntity picEntity = new PicEntity();
		picEntity.setX(10);
		picEntity.setY(568);
		picEntity.setPic(pic);
		
		//////////
		
		QrcodeEntity qrcodeEntity=new QrcodeEntity();
		qrcodeEntity.setContent(content);
		//int YELLOW = 0xFF743018;
		//int NOCOLOR = 0x000000;
		int BGCOLOR=0xFFebd9a0;
		int FRONTCOLOR=0xFF3b3029;
		qrcodeEntity.setFrontColor(FRONTCOLOR);
		qrcodeEntity.setBgColor(BGCOLOR);
		qrcodeEntity.setX(80);
		qrcodeEntity.setY(1139);
		qrcodeEntity.setCode_width(140);
		//qrcodeEntity.setFrontColor(YELLOW);
		
		BufferedImage ret=QrcodeSpiderWebPlotUtil.getTextPicAndQrcode(bgImg,strs,qrcodeEntity,picEntity);
		return ret;
	}
	
	private static int getAdjustLength(String str) {
		return str.length()*25;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedImage ret=ChartUtil.getTextPicAndQrcode("http://wx.sunfintech.com.cn/mobile", " 小白", "10", "英磅", "scyd.png");
		FileOutputStream out = new FileOutputStream("/sunyard/jar/sunvuejob/testGetTextPicAndQrcode.png");
		ChartUtilities.writeBufferedImageAsPNG(out, ret);
	}

}
