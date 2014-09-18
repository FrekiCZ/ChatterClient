package cz.sam.components;

import cz.sam.window.Window;

public abstract class WComponent {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWindowCenter(Window window) {
		return window.getWidth() / 2 - this.width / 2;
	}
	
	public int getHeightWindowCenter(Window window) {
		return window.getHeight() / 2 - this.getHeight() / 2 - 5;
	}
	
	public abstract void addToWindow(Window window);
	
}
