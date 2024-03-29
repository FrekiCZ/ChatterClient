package cz.sam.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Util {
	
	public static void openURI(String par1String) {
		try {
			Desktop.getDesktop().browse(new URI(par1String));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String webResponse(String par1String) {
		String content = "";
		
		try {
			URL url = new URL(par1String);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = "";
			while((line = reader.readLine()) != null) {
				content += line;
			}
			reader.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return content;
	}
	
	public static String readFile(String filename) {
		String content = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line = "";
			while((line = reader.readLine()) != null) {
				content += line;
			}
			reader.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}
	
	public static boolean FileExist(String file) {
		boolean flag = false;
		if(new File(file).exists()) flag = true;
		return flag;
	}
	
	public static boolean directoryExist(String folder) {
		boolean flag = false;
		File file = new File(folder);
		if(file.exists()) {
			if(file.isDirectory()) flag = true;
		}
		return flag;
	}
	
	public static void createDirectory(String folder) {
		new File(folder).mkdirs();
	}
	
	public static void createFile(String file) {
		try {
			new File(file).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
