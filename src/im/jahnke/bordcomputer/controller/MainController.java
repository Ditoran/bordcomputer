package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;

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
			window.getLogsTable().addRoute(route);
		}
	}
	
	public void initActionListeners(){
		
		window.getMenuPanel().getMenuItemConnectSD().addActionListener(ae ->{
			DeviceDialog.showDialog(this);
		});
		window.getMenuPanel().getMenuItemAbout().addActionListener(ae -> {
			JOptionPane.showMessageDialog(null, "BordComputer Version 0.1.1", "Über...", JOptionPane.INFORMATION_MESSAGE);
		});
		
		window.getMenuPanel().getMenuItemConnectUSB().addActionListener(ae -> {});
		
		window.getMenuPanel().getMenuItemExit().addActionListener(ae -> {});
		
		window.getMenuPanel().getMenuItemExport().addActionListener(ae -> {});
		
		window.getMenuPanel().getMenuItemImport().addActionListener(ae -> {});
		
		window.getMenuPanel().getMenuItemSettings().addActionListener(ae -> {});
		
		window.getLogsTable().getView().addMouseListener(this);
		window.getLogsTable().getView().setName("LogFilesTable");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() >= 2 && e.getSource() instanceof JTable && ((JTable)e.getSource()).getName().equals("LogFilesTable")){
			JTable table = (JTable)e.getSource();
			Logger.log("Row: " + table.rowAtPoint(e.getPoint()));
			Route route = window.getLogsTable().getModel().getAllData().get(table.rowAtPoint(e.getPoint()));
			Logger.log("" + route.toString());
			window.getMapPanel().fillMapPanel(route);
		}
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
