package cz.sam.events;

import java.awt.event.WindowEvent;

public interface IWindowEvent {
	
	void windowOpened(WindowEvent event);
	
	void windowClosing(WindowEvent event);
	
}
