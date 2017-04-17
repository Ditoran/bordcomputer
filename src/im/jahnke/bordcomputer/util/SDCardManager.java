package im.jahnke.bordcomputer.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import im.jahnke.bordcomputer.Logger;

public class SDCardManager implements IDeviceManager {

	private String defaultDevice;
	
	@Override
	public String[] listDevices() {
		File[] f = File.listRoots();
		String[] devices = new String[f.length];
		for (int i = 0; i < f.length; i++) {
			devices[i] = f[i].toString();
		}
		return devices;
	}

	@Override
	public String findDevice() {
		for (String s : listDevices()) {
			File f = new File(s + ".BORD");
			if(f.exists()){
				Logger.log(String.format("SD-Karte gefunden (%s)", s));
				return s;
			}
		}
		return null;
	}

	@Override
	public String[] listLogs() {
		File[] files = new File(defaultDevice + "LOGS").listFiles();
		
		String[] result = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			result[i] = files[i].getName();
		}
		return result;
	}

	@Override
	public String getLog(String logName) {
		StringBuilder sb = new StringBuilder();
		try (	
			FileInputStream fis = new FileInputStream(defaultDevice + "LOGS\\" + logName);
			Scanner sc = new Scanner(fis);
		){
			while(sc.hasNextLine()){
				sb.append(sc.nextLine());
				sb.append(System.lineSeparator());
			}
		} catch (Exception e) {
			return "";
		}
		return sb.toString();
	}

	@Override
	public int getDeviceType() {
		return SD_CARD;
	}

	@Override
	public void setDefaultDevice(String device) {
		this.defaultDevice = device;
	}

}
