package im.jahnke.bordcomputer.gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import im.jahnke.bordcomputer.Route;

public class DiagramPanel extends JPanel {

	private static final long serialVersionUID = 520932552294620954L;
	
	private Route route;
	
	JComboBox<String> comboBox = new JComboBox<>();
	
	public DiagramPanel() {
		comboBox.addItem("Geschwindigkeit");
		comboBox.addItem("Höhe");
		comboBox.addItem("Temperatur");
		comboBox.addItem("Sprit");
	}
	
	public void setRoute(Route route){
		this.route = route;
	}

}
