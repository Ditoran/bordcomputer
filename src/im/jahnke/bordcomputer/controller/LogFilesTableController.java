package im.jahnke.bordcomputer.controller;

import im.jahnke.bordcomputer.gui.LogFilesTable;
import im.jahnke.bordcomputer.model.LogFilesTableModel;

public class LogFilesTableController {
	
	private LogFilesTableModel model;
	private LogFilesTable table;
	
	
	public LogFilesTableController(MainController c) {
		this.model = new LogFilesTableModel(this);
		this.table = new LogFilesTable(this);
		
	}
	
	public LogFilesTable getView(){
		return table;
	}
	
}
