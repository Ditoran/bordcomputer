package im.jahnke.bordcomputer.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.controller.ActionCommands;
import im.jahnke.bordcomputer.controller.MainController;
import im.jahnke.bordcomputer.misc.DeviceManager;

public class DeviceDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209017752215746474L;
	private static DeviceDialog instance;
	
	private DeviceDialog() {
		
	}
	
	public static void showDialog(MainController controller){
		instance = new DeviceDialog();
		instance.setModal(true);
		//instance.setLayout(null);
		instance.setSize(400, 300);
		instance.setLocationRelativeTo(null);		
		
		JComboBox<String> externalDrives = new JComboBox<String>();       

		String[] logs = DeviceManager.getDevices();
		//String[] logs = {"C:\\", "D:\\"};
		
		Arrays.sort(logs);
		for (String device : logs) {
			externalDrives.addItem(device);
		}
		externalDrives.setActionCommand(ActionCommands.DEVICE_DIALOG);	
		externalDrives.setSelectedItem(DeviceManager.findDevice());
		
		instance.getContentPane().add(externalDrives, BorderLayout.NORTH);
		
		JButton closeButton = new JButton("Ok");	
		closeButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeviceManager.setDefaultDevice(externalDrives.getSelectedItem().toString());
				Logger.log("Device " + DeviceManager.getDefaultDevice() + " is now set as default");
				controller.loadFilesFromDevice();
				instance.dispose();
			}
		});
		instance.getContentPane().add(closeButton, BorderLayout.SOUTH);
		
		instance.setVisible(true);
		
		
	}
	
}
