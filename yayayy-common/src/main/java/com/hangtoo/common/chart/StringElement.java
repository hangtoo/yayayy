package com.hangtoo.common.chart;

import java.awt.Color;
import java.awt.Font;

public class StringElement {
	String content;
	Color color;
	Font font;
	
	public StringElement(String content,Color color,Font font) {
		this.content=content;
		this.color=color;
		this.font=font;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	
}
