package im.jahnke.bordcomputer.misc;

import java.io.File;

public class DeviceManager {
	
	private static String defaultDevice = "";

	public static String[] getDevices() {
		File[] f = File.listRoots();
		String[] devices = new String[f.length];
		for (int i = 0; i < f.length; i++) {
			devices[i] = f[i].toString();
		}		
		return devices;
	}
	
	public static String findDevice(){
		for (String s : getDevices()) {
			File f = new File(s + ".BORD");
			if(f.exists()){
				System.out.println("SD-Karte gefunden!");
				defaultDevice = f.getParent();
				return defaultDevice;
			}
		}
		return null;
	}
	
	public static void setDefaultDevice(String device){
		defaultDevice = device;
	}
	
	public static String getDefaultDevice(){
		return defaultDevice;
	}
	
	public static File[] listLogs(){
		File f = new File(defaultDevice + "LOGS");
		return f.listFiles();
		
	}
	
	public static File[] listDummyLogs(){
		File[] f = new File[5];
		for (int i = 0; i < f.length; i++) {
			f[i] = new File(System.currentTimeMillis()+".txt");
		}
		return f;
	}

}
