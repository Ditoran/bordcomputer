package im.jahnke.bordcomputer;

import javax.swing.JEditorPane;

public class Logger<T> {
	
	private static JEditorPane object;
	
	public static void add(JEditorPane pane){
		object = pane;
	}
	
	public static void log(String log){
		object.setText(object.getText()+ log + "\n");
		object.setCaretPosition(object.getText().length());
		object.repaint();
	}
	
}
