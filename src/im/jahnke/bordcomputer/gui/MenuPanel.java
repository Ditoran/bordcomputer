package im.jahnke.bordcomputer.gui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import im.jahnke.bordcomputer.controller.ActionCommands;
import im.jahnke.bordcomputer.controller.MainController;

public class MenuPanel extends JPanel{
	
	private static final long serialVersionUID = -4626919742336966027L;
	
	private JToolBar toolBar;
	private JButton button;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuConnect;
	private JMenu menuEdit;
	private JMenu menuHelp;
	private JMenuItem menuItemConnectSD;
	private JMenuItem menuItemConnectUSB;
	private JMenuItem menuItemImport;
	private JMenuItem menuItemExport;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemSettings;
	private JMenuItem menuItemAbout;
	
	public MenuPanel() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        menuBar = new JMenuBar();
        menuFile = new JMenu("Datei");
        menuConnect = new JMenu("Verbinden");
        menuEdit = new JMenu("Bearbeiten");
        menuHelp = new JMenu("Hilfe"); 
        
        menuItemConnectSD = new JMenuItem("SD-Karte");
        menuItemConnectUSB = new JMenuItem("USB");
        menuItemImport = new JMenuItem("Importieren");
        menuItemExport = new JMenuItem("Exportieren");
        menuItemExit = new JMenuItem("Beenden");
        menuItemSettings = new JMenuItem("Einstellungen");
        menuItemAbout = new JMenuItem("Über");
        
        menuBar.add(menuFile);
        menuFile.add(menuConnect);
        menuConnect.add(menuItemConnectSD);
        menuConnect.add(menuItemConnectUSB);
        menuFile.addSeparator();
        menuFile.add(menuItemImport);
        menuFile.add(menuItemExport);
        menuBar.add(menuEdit);
        menuEdit.add(menuItemSettings);        
        menuBar.add(menuHelp);
        menuHelp.add(menuItemAbout);
        
        menuBar.add(Box.createHorizontalGlue());        
        
        this.add(menuBar);
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        button = new JButton();
        button.setIcon(new ImageIcon("icons/usb.png"));

        toolBar.add(button);
        toolBar.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(toolBar);
        
        
	}

	public JMenuItem getMenuItemConnectSD() {
		return menuItemConnectSD;
	}

	public JMenuItem getMenuItemConnectUSB() {
		return menuItemConnectUSB;
	}

	public JMenuItem getMenuItemImport() {
		return menuItemImport;
	}

	public JMenuItem getMenuItemExport() {
		return menuItemExport;
	}

	public JMenuItem getMenuItemExit() {
		return menuItemExit;
	}

	public JMenuItem getMenuItemSettings() {
		return menuItemSettings;
	}

	public JMenuItem getMenuItemAbout() {
		return menuItemAbout;
	}
	
	

}
