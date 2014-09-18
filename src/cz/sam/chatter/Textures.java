package cz.sam.chatter;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Textures {
	
	//public static Textures ICON = new Textures(Lauchner.getFile("/data/icon_32.png")).load();
	
	private URL url;
	private Icon icon;
	private BufferedImage bImage;
	
	public Textures(URL url) {
		this.url = url;
	}
	
	public Textures load() {
		try {
			this.icon = new ImageIcon(this.url);
			this.bImage = ImageIO.read(this.url);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return this;
	}
	
	public Icon getIcon() {
		return this.icon;
	}
	
	public BufferedImage getImage() {
		return this.bImage;
	}
	
}
