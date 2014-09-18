package cz.sam.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;

import cz.sam.window.Window;

public class WLabel extends WComponent {
	
	private JLabel label;
	private String text;
	private Font font;
	private Color color;
	
	public WLabel() {
		
	}
	
	public void setText(String text) {
		this.text = text;
		this.updateText(text);
	}
	
	private void updateText(String text) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		Font font = null;
		if(this.font != null) {
			font = this.font;
		} else {
			font = new Font("Sans Serif", Font.PLAIN, 14);
		}
		
		//this.width = text.length() * font.getSize();
		//this.height = font.getSize() + 4;
		
		this.width = (int)(font.getStringBounds(text, frc).getWidth());
		this.height = (int)(font.getStringBounds(text, frc).getHeight());
	}
	
	public void setTextColor(Color color) {
		this.color = color;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override
	public void addToWindow(Window window) {
		this.label = new JLabel(this.text);
		this.label.setBounds(this.x, this.y, this.width, this.height);
		if(this.font != null) {
			this.label.setFont(this.font);
		}
		if(this.color != null) {
			this.label.setForeground(this.color);
		}
		window.addComponenet(this.label);
	}
	
}
