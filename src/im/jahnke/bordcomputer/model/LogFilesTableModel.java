package im.jahnke.bordcomputer.model;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import im.jahnke.bordcomputer.controller.LogFilesTableController;

public class LogFilesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 9115406942124496525L;
	
	String[] columns = {"ID", "Datum", "Lat", "Lon", "Open"};
	ArrayList<LogFileTableData> data = new ArrayList<>();
	
	
	
	LogFilesTableController controller;
	
	public LogFilesTableModel(LogFilesTableController controller) {
		this.controller = controller;
		
		data.add(new LogFileTableData(1, new Date(), 3.14, 2.7172, ""));
	}
	
	public void readLogFiles(){
		
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		/*String[] array = data.get(rowIndex);
		array[columnIndex] = (String) aValue;
		data.set(rowIndex, array);*/
		//fireTableCellUpdated(rowIndex, columnIndex);
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
			return data.get(arg0).id;
		case 1:
			return data.get(arg0).date;
		case 2:
			return data.get(arg0).latitude;
		case 3:
			return data.get(arg0).longitude;
		case 4:
			return data.get(arg0).open;
		default:
			return "";
		
		}
	}
	
	class LogFileTableData
	{
		private int id;
		private Date date;
		private double latitude;
		private double longitude;
		private String open;

		public LogFileTableData(int id, Date date, double latitude, double longitude, String open) {
			this.id = id;
			this.date = date;
			this.latitude = latitude;
			this.longitude = longitude;
			this.open = open;
		}
		
	}

}
