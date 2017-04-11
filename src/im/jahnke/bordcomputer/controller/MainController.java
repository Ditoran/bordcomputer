package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.gui.DeviceDialog;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.misc.DeviceManager;
import im.jahnke.bordcomputer.model.Model;

public class MainController implements ActionListener, MouseListener {
	
	private Model model;
	private MainWindow window;
		
	public MainController(MainWindow window) {
		this.model = new Model();
		this.window = window;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ActionCommands.DEVICE_DIALOG)){
			for (File file : DeviceManager.listLogs()) {
				Route route = new Route(file);
				Logger.log("Reading file " + file.getName());
				window.addRoute(route);
			}
		} else if(e.getActionCommand().equals(ActionCommands.TOOLBAR_USB)){
			
		} else {
			System.out.println("Else: " + e.getSource());
		}
	}
	

	public void connectButtonActionPerformed(ActionEvent e) {
			DeviceDialog.showDialog(this);
			
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
