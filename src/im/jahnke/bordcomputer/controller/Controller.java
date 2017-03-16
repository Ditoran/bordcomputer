package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.gui.Window;
import im.jahnke.bordcomputer.model.Model;

public class Controller implements ActionListener, MouseListener {
	
	Window window;
	Model model;
	
	public Controller() {
		this.window = new Window(this);
		this.model = new Model();
		this.model.addObserver(window);
		Logger.getInstance().addObserver(window);
		Logger.log("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("usb")){
			Logger.log("USB clicked");
		} else if(e.getActionCommand().equals("")){
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable){
			JTable logsTable = (JTable)e.getSource();
			int row = logsTable.rowAtPoint(e.getPoint());
			int column = logsTable.columnAtPoint(e.getPoint());
			logsTable.addColumnSelectionInterval(0, logsTable.getModel().getColumnCount()-1);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
