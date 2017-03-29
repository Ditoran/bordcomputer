package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.gui.DeviceDialog;
import im.jahnke.bordcomputer.gui.MenuPanel;

public class MenuController implements ActionListener{
	
	MainController controller;
	MenuPanel panel;
	
	public MenuController(MainController controller) {
		this.controller = controller;
		panel = new MenuPanel(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ActionCommands.TOOLBAR_USB)){
			Logger.log("USB clicked");
			DeviceDialog.showDialog(this);
		} else if(e.getActionCommand().equals(ActionCommands.DEVICE_DIALOG)){
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>)(e.getSource());
			Logger.log("Device selected: " + box.getSelectedItem());
			
		}
	}
	
	public MenuPanel getPanel(){
		return panel;
	}
	
}
