package im.jahnke.bordcomputer;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import im.jahnke.bordcomputer.controller.MainController;
import im.jahnke.bordcomputer.gui.MainWindow;

public class Main {

	

	public static void main(String[] args) throws Exception{
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new MainWindow();
			}
		});		

	}
}
