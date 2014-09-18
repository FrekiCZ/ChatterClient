package cz.sam.chatter.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import cz.sam.chatter.network.packets.Packet;

public class TCPConnection {
	
	private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    
    private boolean isReadThreadRunning = false;
    private boolean isWriteThreadRunning = false;
    private Thread readThread;
    private Thread writeThread;
    
    private Queue<Packet> writeQueue;
    
    public TCPConnection(Socket socket) {
        try {
            this.socket = socket;
            this.output = new DataOutputStream(socket.getOutputStream());
            this.input = new DataInputStream(socket.getInputStream());
            this.writeQueue = new LinkedList<>();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        this.isReadThreadRunning = true;
        this.isWriteThreadRunning = true;
        this.readThread();
        this.writeThread();
    }
    
    private void readThread() {
        this.readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isReadThreadRunning) {
                    try {
                        Packet.recivePackets(input);
                    } catch(Exception ex) {
                    	ex.printStackTrace();
                        stop();
                    }
                }
            }
            
        });
        this.readThread.start();
    }
    
    private void writeThread() {
        this.writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isWriteThreadRunning) {
                    if(writeQueue.size() > 0) {
                        try {
                            Packet packet = writeQueue.remove();
                            Packet.sendPacket(packet, output);
                        } catch(Exception ex) {
                        	ex.printStackTrace();
                            stop();
                        }
                    }
                }
            }
            
        });
        this.writeThread.start();
    }
    
    public void stop() {
        try {
            this.isReadThreadRunning = false;
            this.isWriteThreadRunning = false;
            this.readThread.interrupt();
            this.writeThread.interrupt();
            this.socket.close();
        } catch (IOException ex) { }
    }
    
    public void sendPacket(Packet packet) {
        this.writeQueue.add(packet);
    }
	
}
