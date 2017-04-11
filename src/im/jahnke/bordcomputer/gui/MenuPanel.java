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
	private JMenu menuEdit;
	private JMenuItem item;
	
	public MenuPanel(MainController controller) {
		
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
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               controller.connectButtonActionPerformed(e);
            }
         });
        toolBar.add(button);
        toolBar.add(Box.createHorizontalGlue());
        
        this.add(menuBar);
        this.add(Box.createHorizontalGlue());
        this.add(toolBar);
        
        
	}

}
