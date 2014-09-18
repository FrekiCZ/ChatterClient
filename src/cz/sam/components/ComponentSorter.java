package cz.sam.components;

import java.util.ArrayList;
import java.util.List;

import cz.sam.window.Window;

public class ComponentSorter {
	
	private List<WComponent> components;
	private int y;
	private int distance = 5;
	
	public ComponentSorter() {
		this.components = new ArrayList<WComponent>();
	}
	
	public void addComponent(WComponent wcomponent) {
		this.components.add(wcomponent);
	}
	
	public void sort() {
		this.preSort(null);
	}
	
	public void sort(Window window) {
		this.preSort(window);
	}
	
	private void preSort(Window window) {
		int index = -1;
		for(WComponent wcomponent : this.components) {
			index++;
			this.y += wcomponent.getHeight() + this.distance;
			int addY = index == 0 ? this.y : y;
			wcomponent.setY(addY);
			if(window != null) wcomponent.addToWindow(window);
		}
	}
	
	public void setStartY(int y) {
		this.y = y;
	}
	
	public void setYDistance(int distance) {
		this.distance = distance;
	}
	
}
