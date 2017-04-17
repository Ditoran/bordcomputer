package im.jahnke.bordcomputer.util;

public class DeviceManagerFactory {
		
	private static IDeviceManager manager;
	private static boolean connected = false;
	
	private DeviceManagerFactory(){
		
	}
	
	public static boolean connect(){
		//check USB
		
		//CHECK SD
		SDCardManager sd = new SDCardManager();
		USBDeviceManager usb = new USBDeviceManager();
		
		if(sd.findDevice()==null){
			if(sd.findDevice()==null){
				return false;
			} else {
				manager = usb;
				manager.setDefaultDevice(usb.findDevice());
				connected = true;
				return connected;
			}
		} else {
			manager = sd;
			manager.setDefaultDevice(sd.findDevice());
			connected = true;
			return connected;
		}
		
	}
	
	public static IDeviceManager getInstance(){
		return manager;
	}

}
