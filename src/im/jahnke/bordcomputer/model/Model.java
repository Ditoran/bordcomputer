package im.jahnke.bordcomputer.model;

import java.io.File;
import java.util.Observable;

import im.jahnke.bordcomputer.util.DeviceManager;

public class Model extends Observable {
	
	private String device = "";
	
	public Model(){
		
	}
	
	public void connectDevice(String device){
		this.device = device;
		DeviceManager.setDefaultDevice(device);
		File[] logFiles = DeviceManager.listLogs();
	}
	
	private void loadLogsToTableModel(){
		
	}
	
	public void setTableData(){
		
	}
	
}
