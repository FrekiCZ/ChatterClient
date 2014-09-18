package cz.sam.components;

import javax.swing.Icon;
import javax.swing.JLabel;

import cz.sam.chatter.Textures;
import cz.sam.window.Window;

public class WImageBox extends WComponent {
	
	private JLabel imgBox;
	private boolean autoSize;
	private Icon image;
	
	public WImageBox(int x, int y) {
		this.x = x;
		this.y = y;
		this.autoSize = true;
	}
	
	public WImageBox(int x, int y, int width, int height, Textures texture) {
		this(x, y);
		this.width = width;
		this.height = height;
		this.image = texture.getIcon();
		this.autoSize = false;
	}
	
	public WImageBox(int x, int y, Textures texture) {
		this(x, y);
		this.image = texture.getIcon();
	}
	
	@Override
	public void addToWindow(Window window) {
		this.imgBox = new JLabel(this.image);
		if(this.autoSize) {
			this.imgBox.setBounds(this.x, this.y, this.image.getIconWidth(), this.image.getIconHeight());
		} else {
			this.imgBox.setBounds(this.x, this.y, this.width, this.height);
		}
		window.addComponenet(this.imgBox);
	}
	
	public void setImage(Textures texture) {
		this.image = texture.getIcon();
	}
	
}
