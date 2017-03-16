package im.jahnke.bordcomputer;

import im.jahnke.bordcomputer.controller.Controller;

public class Main {

	

	public static void main(String[] args) throws Exception{

		System.out.println(DeviceManager.getDevice());
		
		new Controller();
		//new Database();
		try {
			//new SerialConnection().connect("COM6");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
