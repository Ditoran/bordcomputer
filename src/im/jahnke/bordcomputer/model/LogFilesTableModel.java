package im.jahnke.bordcomputer.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class LogFilesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 9115406942124496525L;
	
	String[] columns = {"ID", "Datum", "Lat", "Lon", "Open"};
	ArrayList<String> data = new ArrayList<>();

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
