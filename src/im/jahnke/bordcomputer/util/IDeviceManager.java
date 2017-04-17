package im.jahnke.bordcomputer.util;

public interface IDeviceManager {

	public static final int USB_DEVICE = 1;
	public static final int SD_CARD = 2;
	
	public String[] listDevices();
	
	public String findDevice();
	
	public void setDefaultDevice(String device);
	
	public String[] listLogs();
	
	public String getLog(String logName);
	
	public int getDeviceType(); 
	
}
