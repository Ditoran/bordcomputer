package im.jahnke.bordcomputer.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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
import im.jahnke.bordcomputer.controller.Controller;

public class MenuPanel extends JPanel{
	
	private static final long serialVersionUID = -4626919742336966027L;
	
	JToolBar toolBar;
	JButton button;
	JMenuBar menuBar;
	JMenu menuFile;
	JMenu menuEdit;
	JMenuItem item;
	
	public MenuPanel(Controller controller) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        menuBar = new JMenuBar();
        menuFile = new JMenu("Datei");
        menuEdit = new JMenu("Edit");        
        item = new JMenuItem("Öffnen");        
        menuFile.add(item);        
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(Box.createHorizontalGlue());

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        button = new JButton();
        button.setIcon(new ImageIcon("icons/usb.png"));
        button.setMargin(new Insets(0,0,0,0));
        button.setActionCommand(ActionCommands.TOOLBAR_USB);
        button.addActionListener(controller);
        toolBar.add(button);
        toolBar.add(Box.createHorizontalGlue());
        
        this.add(menuBar);
        this.add(Box.createHorizontalGlue());
        this.add(toolBar);
        
        
	}

}
