package im.jahnke.bordcomputer;

import javax.swing.SwingUtilities;

import im.jahnke.bordcomputer.controller.MainController;

public class Main {

	

	public static void main(String[] args) throws Exception{
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new MainController();
			}
		});		

	}
}
