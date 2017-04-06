package im.jahnke.bordcomputer.gui;

import javax.swing.JTable;

import im.jahnke.bordcomputer.controller.LogFilesTableController;
import im.jahnke.bordcomputer.model.LogFilesTableModel;

public class LogFilesTable extends JTable {

	private static final long serialVersionUID = -1073289710515753940L;

	public LogFilesTable(LogFilesTableController logFilesTableController) {
		setModel(new LogFilesTableModel(logFilesTableController));
		addMouseListener(logFilesTableController);
	}
	
}
