package cz.sam.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

import cz.sam.chatter.Textures;
import cz.sam.events.IWButtonEvent;
import cz.sam.window.Window;

public class WButton extends WComponent {
	
	private int id;
	private String string;
	private JButton button;
	private Icon buttonIcon;
	private Icon hoverButtonIcon;
	private IWButtonEvent listener;
	private boolean visible = true;
	private boolean enabled = true;
	
	public WButton(int id, int x, int y, int width, int height, String string) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.string = string;
	}
	
	public WButton(int id, int x, int y, int width, String string) {
		this(id, x, y , width, 25, string);
	}
	
	public WButton(int id, int x, int y, String string) {
		this(id, x, y , 120, 25, string);
	}
	
	public WButton(int id, int x, int y) {
		this(id, x, y , 120, 25, null);
	}
	
	public WButton(int id) {
		this(id, 0, 0 , 0, 0, null);
	}
	
	public WButton(int id, String string) {
		this(id, 0, 0 , 0, 0, string);
	}
	
	public void setCenterLocation(Window window, int y) {
		this.x = window.getWidth() / 2 - this.width / 2 - 5;
		this.y = y;
	}
	
	public void addClickListener(final IWButtonEvent event) {
		this.listener = event;
	}
	
	@Override
	public void addToWindow(Window window) {
		if(this.buttonIcon != null) {
			this.button = new JButton(this.string);
			this.button.setIcon(this.buttonIcon);
			this.button.setForeground(Color.WHITE);
			this.button.setHorizontalTextPosition(AbstractButton.CENTER);
			this.button.setBorder(BorderFactory.createEmptyBorder());
			this.button.setFont(new Font("Microsoft Sans Serif", Font.TRUETYPE_FONT, 16));
			this.button.setVisible(this.visible);
			this.button.setEnabled(this.enabled);
			this.button.setContentAreaFilled(false);
		} else {
			this.button = new JButton(this.string);
			this.button.setVisible(this.visible);
			this.button.setEnabled(this.enabled);
			this.button.setFont(new Font("Sans Serif", Font.TRUETYPE_FONT, 14));
			this.button.setHorizontalTextPosition(AbstractButton.CENTER);
		}
		
		final WButton bt = this;
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.buttonClicked(bt);
			}
			
		});
		
		this.button.setBounds(this.x, this.y, this.width, this.height);
		this.button.setFocusable(false);
		if(this.hoverButtonIcon != null) {
			this.button.setRolloverIcon(this.hoverButtonIcon);
		}
		window.addComponenet(this.button);
	}
	
	public void setBackground(Textures texture) {
		this.buttonIcon = texture.getIcon();
	}
	
	public void setHoveredButton(Textures texture) {
		this.hoverButtonIcon = texture.getIcon();
	}
	
	public void setVisible(boolean flag) {
		this.visible = flag;
	}
	
	public void setEnabled(boolean flag) {
		this.enabled = flag;
	}
	
	public int getID() {
		return this.id;
	}
	
}
