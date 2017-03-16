package im.jahnke.bordcomputer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.gui.Window;
import im.jahnke.bordcomputer.model.Model;

public class Controller implements ActionListener {
	
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
	
	

}
