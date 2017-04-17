package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.TrackPoint;
import im.jahnke.bordcomputer.gui.MainWindow;
import im.jahnke.bordcomputer.util.DeviceManagerFactory;

public class MainController implements MouseListener {
	
	private MainWindow window;
		
	public MainController(MainWindow window) {
		this.window = window;
		initActionListeners();
		deviceDetection();
	}

	public void connectButtonActionPerformed(ActionEvent e) {
	}
	
	public void loadFilesFromDevice(){
		String[] logFiles = DeviceManagerFactory.getInstance().listLogs();
		for (String file : logFiles) {
			int id = Integer.parseInt(file.split("\\.")[0]);
			Route route = new Route(id, DeviceManagerFactory.getInstance().getLog(file));
			window.getLogFilesTable().addRoute(route);
		}
	}
	
	public void initActionListeners(){
		
		window.getMenuPanel().getMenuItemConnectSD().addActionListener(ae -> {});
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
					//search for SD card
					if(DeviceManagerFactory.connect()){
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
		if(e.getSource() instanceof JTable && ((JTable)e.getSource()).getName().equals("LogFilesTable")){
			JTable table = (JTable)e.getSource();
			Route route = window.getLogFilesTable().getModel().getAllData().get(table.rowAtPoint(e.getPoint()));
			Logger.log("" + route.toString());
			window.getMapPanel().fillMapPanel(route);
			
			window.getTrackPointTable().getModel().getAllData().clear();
			for (TrackPoint trackpoint : route.getTrackPoints()) {
				window.getTrackPointTable().getModel().addData(trackpoint);
			}
			
			window.getLogDetailsPanel().setData(route);
		}
		if(e.getSource() instanceof JTable && ((JTable)e.getSource()).getName().equals("TrackPointsTable")){
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
