package im.jahnke.bordcomputer;

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
	
	public static String getDevice(){
		for (String s : getDevices()) {
			File f = new File(s + ".BORD");
			if(f.exists()){
				System.out.println("SD-Karte gefunden!");
				defaultDevice = f.getParent();
				return defaultDevice;
			}
		}
		return "";
	}
	
	public static void setDefaultDevice(String device){
		defaultDevice = device;
	}
	
	public static File[] listLogs(){
		File f = new File(defaultDevice + "LOGS");
		return f.listFiles();
		
	}

}
