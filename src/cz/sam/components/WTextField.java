package cz.sam.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cz.sam.window.Window;

public class WTextField extends WComponent {
	
	private JTextField textField;
	private JPasswordField passFiled;
	private boolean textCensored = false;
	private boolean textInCenter = false;
	private boolean emptyBorder = false;
	private Color backgroundColor;
	
	public WTextField() { }
	
	@Override
	public void addToWindow(Window window) {
		if(this.textCensored) {
			this.openPassField(window);
		} else {
			this.openTextField(window);
		}
	}
	
	public void textCensored(boolean flag) {
		this.textCensored = flag;
	}
	
	private void openTextField(Window window) {
		this.textField = new JTextField();
		this.textField.setBounds(this.x, this.y, this.width, this.height);
		if(this.backgroundColor != null)
			this.textField.setBackground(this.backgroundColor);
		if(this.emptyBorder) {
			this.passFiled.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		}
		this.textField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		if(this.textInCenter) this.textField.setHorizontalAlignment(JTextField.CENTER);
		window.addComponenet(this.textField);
	}
	
	private void openPassField(Window window) {
		this.passFiled = new JPasswordField();
		this.passFiled.setBounds(this.x, this.y, this.width, this.height);
		this.passFiled.setBackground(new Color(110, 110, 110));
		this.passFiled.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		this.passFiled.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		this.passFiled.setForeground(new Color(200, 200, 200));
		if(this.textInCenter) this.passFiled.setHorizontalAlignment(JTextField.CENTER);
		window.addComponenet(this.passFiled);
	}
	
	public void setBackground(Color color) {
		
	}
	
	public void setEmptyBorder() {
		
	}
	
	public String getText() {
		if(this.textCensored) {
			return String.valueOf(this.passFiled.getPassword());
		} else {
			return this.textField.getText();
		}
	}
	
	public void setTextCenter(boolean flag) {
		this.textInCenter = flag;
	}
	
}
