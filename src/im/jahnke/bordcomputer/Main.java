package im.jahnke.bordcomputer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import im.jahnke.bordcomputer.gui.Window;

public class Main {

	

	public static void main(String[] args) throws Exception{

		System.out.println(DeviceManager.getDevice());
		
		new Window();
		//new Database();
		try {
			//new SerialConnection().connect("COM6");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
