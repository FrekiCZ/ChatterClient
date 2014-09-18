package cz.sam.chatter;

import cz.sam.chatter.window.ChatWindow;
import cz.sam.chatter.window.SetNameWindow;

public class Chatter {
	
	public String username;
	
	public Chatter() {
		
	}
	
	public void start() throws Exception {
		SetNameWindow window = new SetNameWindow(this, 300, 120);
		window.open();
	}
	
	public void loginToChat() {
		ChatWindow chatWindow = new ChatWindow(this, 600, 300);
		chatWindow.open();
	}
	
}
