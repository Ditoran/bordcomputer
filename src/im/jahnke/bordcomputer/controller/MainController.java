package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.TrackPoint;
import im.jahnke.bordcomputer.gui.DeviceDialog;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.model.Model;
import im.jahnke.bordcomputer.util.DeviceManager;

public class MainController implements MouseListener {
	
	private Model model;
	private MainWindow window;
		
	public MainController(Model model, MainWindow window) {
		this.model = model;
		this.window = window;
		initActionListeners();
		deviceDetection();
	}

	public void connectButtonActionPerformed(ActionEvent e) {
			DeviceDialog.showDialog(this);			
	}
	
	public void loadFilesFromDevice(){
		for (File file : DeviceManager.listLogs()) {
			Route route = new Route(file);
			Logger.log("Reading file " + file.getName());
			window.getLogFilesTable().addRoute(route);
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
		
		window.getLogFilesTable().getView().addMouseListener(this);
		window.getLogFilesTable().getView().setName("LogFilesTable");
		
		window.getTrackPointTable().getView().addMouseListener(this);
		window.getTrackPointTable().getView().setName("TrackPointsTable");
	}

	public void deviceDetection(){
		Thread t = new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true){
					String device;
					if((device = DeviceManager.findDevice())!=null){
						DeviceManager.setDefaultDevice(device);
						loadFilesFromDevice();
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() >= 2 && e.getSource() instanceof JTable && ((JTable)e.getSource()).getName().equals("LogFilesTable")){
			JTable table = (JTable)e.getSource();
			Logger.log("Row: " + table.rowAtPoint(e.getPoint()));
			Route route = window.getLogFilesTable().getModel().getAllData().get(table.rowAtPoint(e.getPoint()));
			Logger.log("" + route.toString());
			window.getMapPanel().fillMapPanel(route);
			
			window.getTrackPointTable().getModel().getAllData().clear();
			for (TrackPoint trackpoint : route.getTrackPoints()) {
				window.getTrackPointTable().getModel().addData(trackpoint);
			}
		}
		if(e.getClickCount() >= 2 && e.getSource() instanceof JTable && ((JTable)e.getSource()).getName().equals("TrackPointsTable")){
			JTable table = (JTable)e.getSource();
			Logger.log("Row: " + table.rowAtPoint(e.getPoint()));
			TrackPoint trackPoint = window.getTrackPointTable().getModel().getAllData().get(table.rowAtPoint(e.getPoint()));
			Logger.log("" + trackPoint.toString());
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
