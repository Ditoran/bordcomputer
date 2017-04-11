package im.jahnke.bordcomputer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.controller.LogFilesTableController;

public class LogFilesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 9115406942124496525L;
	
	String[] columns = {"ID", "Datum", "Lat", "Lon", "Open"};
	ArrayList<Route> data = new ArrayList<>();
	
	LogFilesTableController controller;
	
	public LogFilesTableModel(LogFilesTableController controller) {
		this.controller = controller;
	}
	
	public void addData(Route route){
		data.add(route);
		Collections.sort(data, new Comparator<Route>() {
			@Override
			public int compare(Route o1, Route o2) {
				return Integer.compare(o1.getID(), o2.getID());
			}
        });
		fireTableDataChanged();
		
	}
	
	public ArrayList<Route> getAllData(){
		return data;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1){
		case 0:
			return data.get(arg0).getID();
		case 1:
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			return formatter.format(data.get(arg0).getStartDate());
		case 2:
			return data.get(arg0).getStartPoint().getLatitude();
		case 3:
			return data.get(arg0).getStartPoint().getLongitude();
		case 4:
			return "";
		default:
			return "";
		
		}
	}
}
