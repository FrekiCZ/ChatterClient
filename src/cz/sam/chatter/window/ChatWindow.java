package cz.sam.chatter.window;

import java.awt.Font;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import cz.sam.chatter.Chatter;
import cz.sam.chatter.network.TCPConnection;
import cz.sam.chatter.network.events.PacketListener;
import cz.sam.chatter.network.events.PacketManager;
import cz.sam.chatter.network.packets.Packet;
import cz.sam.chatter.network.packets.PacketLogin;
import cz.sam.chatter.network.packets.PacketQuit;
import cz.sam.window.Window;

public class ChatWindow extends Window implements PacketListener {
	
	private Chatter chatter;
	public TCPConnection connection;
	public JTextArea chatArea;
	public JScrollPane scrollPane;
	DefaultListModel<String> userList = new DefaultListModel<String>();
	
	public ChatWindow(Chatter chatter, int width, int height) {
		super(width, height, false);
		this.chatter = chatter;
	}

	@Override
	public void preInit() {
		this.connect();
	}
	
	public void connect() {
		try {
			this.connection = new TCPConnection(new Socket("127.0.0.1", 24468));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nelze se připojit k serveru !");
            System.exit(1);
		}
		PacketManager.registerListener(this);
		this.connection.start();
		
		PacketLogin packetLogin = Packet.packetLogin;
		packetLogin.setUsername(this.chatter.username);
		this.connection.sendPacket(packetLogin);
	}

	@Override
	public void init() {
		this.setWindowLocationAtCenter();
		this.setResizable(false);
		
		this.chatArea = new JTextArea();
		this.chatArea.setEditable(false);
		this.chatArea.setFont(new Font("Arial", Font.PLAIN, 12));
		this.chatArea.setLineWrap(true);
		
		
		this.scrollPane = new JScrollPane(this.chatArea);
		this.scrollPane.setBounds(5, 5, 400, 260);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getJFrame().add(scrollPane);
		
		JList<String> playerList = new JList<String>(this.userList);
		playerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		playerList.setLayoutOrientation(JList.VERTICAL);
		playerList.setVisibleRowCount(-1);
		
		JScrollPane usersScrollPane = new JScrollPane(playerList);
		usersScrollPane.setBounds(410, 5, 180, 100);
		usersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getJFrame().add(usersScrollPane);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setFont(new Font("Arial", Font.PLAIN, 12));
		messageArea.setLineWrap(true);
		
		JScrollPane messageScroll = new JScrollPane(this.chatArea);
		messageScroll.setBounds(410, 190, 180, 100);
		messageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getJFrame().add(messageScroll);
		
		this.addUser(this.chatter.username);
	}
	
	public void addChatMessage(String message) {
		JScrollBar vbar = scrollPane.getVerticalScrollBar();
		this.chatArea.append(message + "\n");
        vbar.setValue(vbar.getMaximum());
        vbar.paint(vbar.getGraphics());
        chatArea.scrollRectToVisible(chatArea.getVisibleRect());
        chatArea.paint(chatArea.getGraphics());
	}
	
	public void addUser(String user) {
		this.userList.addElement(user);
	}
	
	public void removeUser(String user) {
		this.userList.removeElement(user);
	}

	@Override
	public void onPacketRecive(Packet packet) {
		if(packet.getPacketID() == 0) {
			this.addUser(((PacketLogin) packet).getUsername());
		}
		
		if(packet.getPacketID() == 1) {
			this.removeUser(((PacketQuit) packet).getUsername());
		}
	}

	@Override
	public void onPacketSending(Packet packet) {
		
	}

}
