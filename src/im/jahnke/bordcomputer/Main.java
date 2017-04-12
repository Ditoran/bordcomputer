package im.jahnke.bordcomputer;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import im.jahnke.bordcomputer.controller.MainController;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.model.Model;

public class Main {

	

	public static void main(String[] args) throws Exception{
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				Model model = new Model();
				MainWindow window = new MainWindow();
				new MainController(model, window);
			}
		});		

	}
}
