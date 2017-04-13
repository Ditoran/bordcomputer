package im.jahnke.bordcomputer.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.TrackPoint;
import im.jahnke.bordcomputer.model.TrackPointTableModel;

public class TrackPointTableController implements MouseListener{
	
	private TrackPointTableModel model;
	private JTable table;
	
	
	public TrackPointTableController() {
		this.model = new TrackPointTableModel();
		this.table = new JTable(model);
	}
	
	public JTable getView(){
		return table;
	}
	
	public TrackPointTableModel getModel(){
		return model;
	}
	
	public void addRoute(TrackPoint trackPoint){
		model.addData(trackPoint);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable){
			this.table.addColumnSelectionInterval(0, table.getModel().getColumnCount()-1);
			int row = table.rowAtPoint(e.getPoint());
			Logger.log(model.getAllData().get(row).toString());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
