package im.jahnke.bordcomputer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import im.jahnke.bordcomputer.TrackPoint;

public class TrackPointTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7034884910615308010L;
	
	String[] columns = {"Zeit", "Latitude", "Longitude", "Altitude", "Teilstrecke", "Geschwindigkeit"};
	ArrayList<TrackPoint> data = new ArrayList<>();
	
	public void addData(TrackPoint route){
		data.add(route);
		Collections.sort(data, new Comparator<TrackPoint>() {
			@Override
			public int compare(TrackPoint o1, TrackPoint o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
        });
		fireTableDataChanged();
		
	}
	
	public ArrayList<TrackPoint> getAllData(){
		return data;
	}

	@Override
	public int getRowCount() {
		return data.size();
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			return formatter.format(data.get(rowIndex).getDate());			
		case 1:
			return data.get(rowIndex).getLatitude();
		case 2:
			return data.get(rowIndex).getLongitude();
		case 3:
			return "";
		case 4:
			return rowIndex==0 ? 0 : TrackPoint.distanceBetweenTrackPoints(data.get(rowIndex-1), data.get(rowIndex));
		case 5:
			return "";
		default:
			return "";
		
		}
	}

}
