package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.model.Model;

public class MainController implements ActionListener, MouseListener {
	
	private MainWindow window;
	private Model model;
		
	public MainController() {
		this.window = new MainWindow(this);
		this.model = new Model();
		this.model.addObserver(window);
		Logger.getInstance().addObserver(window);
		
		
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ActionCommands.DEVICE_DIALOG)){
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>)(e.getSource());
			Logger.log("Device selected: " + box.getSelectedItem());
			
		} else if(e.getActionCommand().equals(ActionCommands.TOOLBAR_USB)){
			
		} else {
			System.out.println("Else: " + e.getSource());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	

}
