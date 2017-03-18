package im.jahnke.bordcomputer;

import javax.swing.SwingUtilities;

import im.jahnke.bordcomputer.controller.Controller;
import im.jahnke.bordcomputer.misc.GoogleMapsApi;

public class Main {

	

	public static void main(String[] args) throws Exception{
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new Controller();
			}
		});		
		System.out.println(GoogleMapsApi.coordinatesToCity(40.716928,-73.9386837));
	}
}
