package cz.sam.chatter.window;

import cz.sam.chatter.Chatter;
import cz.sam.components.WButton;
import cz.sam.components.WLabel;
import cz.sam.components.WTextField;
import cz.sam.events.IWButtonEvent;
import cz.sam.window.Window;

public class SetNameWindow extends Window implements IWButtonEvent {
	
	private Chatter chatter;
	private WTextField usernameField;
	
	public SetNameWindow(Chatter chatter, int width, int height) {
		super(width, height, false);
		this.chatter = chatter;
	}

	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
		this.setWindowLocationAtCenter();
		this.setResizable(false);
		
		WLabel usernameLabel = new WLabel();
		usernameLabel.setText("Váš nick:");
		usernameLabel.setLocation(usernameLabel.getWindowCenter(this), 2);
		usernameLabel.addToWindow(this);
		
		this.usernameField = new WTextField();
		this.usernameField.setSize(200, 25);
		this.usernameField.setLocation(this.usernameField.getWindowCenter(this), 20);
		this.usernameField.addToWindow(this);
		
		WButton loginButton = new WButton(0, "Login");
		loginButton.setSize(200, 25);
		loginButton.setLocation(loginButton.getWindowCenter(this), 50);
		loginButton.addClickListener(this);
		loginButton.addToWindow(this);
	}

	@Override
	public void buttonClicked(WButton button) {
		if(button.getID() == 0) {
			String username = this.usernameField.getText();
			if(username.isEmpty()) {
				this.showMessgeDialog("Musíte zadat jméno");
				return;
			}
			
			this.chatter.username = username;
			this.close();
			this.chatter.loginToChat();
		}
	}

}
