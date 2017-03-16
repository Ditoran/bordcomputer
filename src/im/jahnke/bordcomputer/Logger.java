package im.jahnke.bordcomputer;

import java.util.Observable;

public class Logger extends Observable {
	
	private static String text = "";
	
	private static Logger instance = null;
	
	private Logger(){
		
	}
	
	public static void log(String log){
		if(instance == null){
			instance = new Logger();
		}		
		
		text += log + "\n";
		instance.setChanged();
		instance.notifyObservers(text);
	}
	
	public static Logger getInstance(){
		return instance;
	}
	
}
