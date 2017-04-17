package im.jahnke.bordcomputer.util;

import im.jahnke.bordcomputer.SerialConnection;

public class USBDeviceManager implements IDeviceManager {
	
	SerialConnection connection = new SerialConnection();

	@Override
	public String[] listDevices() {
		return connection.listSerialPorts();
	}

	@Override
	public String findDevice() {
		return null;
	}

	@Override
	public void setDefaultDevice(String device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] listLogs() {
		return null;
	}

	@Override
	public String getLog(String logName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDeviceType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
