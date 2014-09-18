package cz.sam.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cz.sam.chatter.Textures;
import cz.sam.events.IWindowEvent;

public abstract class Window implements Runnable {
	
	private JFrame jframe;
	private int width;
	private int height;
	private boolean windowsStyle = true;
	private Window parentWindow;
	
	public Window(int width, int height, boolean defaultStyle) {
		this.width = width;
		this.height = height;
		this.windowsStyle = defaultStyle;
	}
	
	public Window(int width, int height) {
		this(width, height, true);
	}
	
	public void open() {
		EventQueue.invokeLater(this);
	}
	
	public void open(Window parent) {
		this.parentWindow = parent;
		EventQueue.invokeLater(this);
	}
	
	public void run() {
		if(!this.windowsStyle) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		this.preInit();
		this.jframe = new JFrame();
		if(this.parentWindow == null) {
			this.jframe.setDefaultCloseOperation(3);
		}
		this.jframe.setSize(this.width, this.height);
		this.jframe.setLayout(null);
		this.defaultWindow();
		init();
		this.jframe.setVisible(true);
	}
	
	private void defaultWindow() {
		this.jframe.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent event) { }

			public void windowClosed(WindowEvent event) { }

			public void windowClosing(WindowEvent event) {
				if(parentWindow != null) {
					parentWindow.setEnabled(true);
					parentWindow.setAutoRequestFocus(true);
				}
			}

			public void windowDeactivated(WindowEvent event) { }

			public void windowDeiconified(WindowEvent event) { }

			public void windowIconified(WindowEvent event) { }

			public void windowOpened(WindowEvent event) { }
			
		});
	}

	public void addWindowListener(final IWindowEvent listener) {
		this.jframe.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent event) { }

			public void windowClosed(WindowEvent event) { }

			public void windowClosing(WindowEvent event) {
				listener.windowClosing(event);
			}

			public void windowDeactivated(WindowEvent event) { }

			public void windowDeiconified(WindowEvent event) { }

			public void windowIconified(WindowEvent event) { }

			public void windowOpened(WindowEvent event) {
				listener.windowOpened(event);
			}
			
		});
	}
	
	public abstract void preInit();
	public abstract void init();
	
	public void update(Object object) {
		
	}
	
	public void showMessgeDialog(String message) {
		JOptionPane.showMessageDialog(this.getJFrame(), message);
	}
	
	public void close() {
		if(this.parentWindow != null) {
			parentWindow.setEnabled(true);
		}
		this.preClose();
		this.jframe.dispose();
		if(this.parentWindow != null) {
			parentWindow.setAutoRequestFocus(true);
			parentWindow.getJFrame().toFront();
		}
	
	}
	
	public void preClose() {
		
	}
	
	public void openDialogWindow(Window window) {
		this.jframe.setEnabled(false);
		window.open(this);
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		this.jframe.getContentPane().setBackground(new Color(r, g, b));
	}
	
	public void setBackgroundColor(Color color) {
		this.jframe.getContentPane().setBackground(color);
	}
	
	public void setTitle(String title) {
		this.jframe.setTitle(title);
	}
	
	public void setWindowLocationAtCenter() {
		this.jframe.setLocationRelativeTo(null);
	}
	
	public void setWindowLocation(int x, int y) {
		this.jframe.setLocation(x, y);
	}
	
	public void setResizable(boolean flag) {
		this.jframe.setResizable(flag);
	}
	
	public void setIcon(Textures texture) {
		this.jframe.setIconImage(texture.getImage());
	}
	
	public void addComponenet(Component component) {
		this.jframe.add(component);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Window getParent() {
		return this.parentWindow;
	}
	
	public void setEnabled(boolean enabled) {
		this.jframe.setEnabled(enabled);
	}
	
	public void setAutoRequestFocus(boolean flag) {
		this.jframe.setAutoRequestFocus(flag);
	}
	
	public JFrame getJFrame() {
		return this.jframe;
	}
	
}