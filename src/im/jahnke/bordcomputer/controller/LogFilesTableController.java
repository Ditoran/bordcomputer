package im.jahnke.bordcomputer.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import im.jahnke.bordcomputer.gui.LogFilesTable;
import im.jahnke.bordcomputer.model.LogFilesTableModel;

public class LogFilesTableController implements MouseListener{
	
	private LogFilesTableModel model;
	private LogFilesTable table;
	
	
	public LogFilesTableController(MainController c) {
		this.model = new LogFilesTableModel(this);
		this.table = new LogFilesTable(this);
		
	}
	
	public LogFilesTable getView(){
		return table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable){
			this.table.addColumnSelectionInterval(0, table.getModel().getColumnCount()-1);
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
