package im.jahnke.bordcomputer.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import im.jahnke.bordcomputer.controller.LogFilesTableController;

public class LogFilesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 9115406942124496525L;
	
	String[] columns = {"ID", "Datum", "Lat", "Lon", "Open"};
	ArrayList<String[]> data = new ArrayList<>();
	
	
	
	LogFilesTableController controller;
	
	public LogFilesTableModel(LogFilesTableController controller) {
		this.controller = controller;
		
		data.add(new String[] {"", "", "", "", ""});
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String[] array = data.get(rowIndex);
		array[columnIndex] = (String) aValue;
		data.set(rowIndex, array);
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
		return data.get(arg0)[arg1];
	}

}
