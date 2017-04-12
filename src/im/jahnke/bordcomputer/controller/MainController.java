package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.gui.DeviceDialog;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.misc.DeviceManager;
import im.jahnke.bordcomputer.model.Model;

public class MainController implements MouseListener {
	
	private Model model;
	private MainWindow window;
		
	public MainController(Model model, MainWindow window) {
		this.model = model;
		this.window = window;
		initActionListeners();
	}

	public void connectButtonActionPerformed(ActionEvent e) {
			DeviceDialog.showDialog(this);			
	}
	
	public void loadFilesFromDevice(){
		for (File file : DeviceManager.listLogs()) {
			Route route = new Route(file);
			Logger.log("Reading file " + file.getName());
			window.addRoute(route);
		}
	}
	
	public void initActionListeners(){
		
		window.getMenuPanel().getMenuItemConnectSD().addActionListener(ae ->{
			DeviceDialog.showDialog(this);
		});
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
