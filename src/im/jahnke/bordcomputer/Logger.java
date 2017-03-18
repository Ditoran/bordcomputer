package im.jahnke.bordcomputer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;

public class Logger extends Observable {
	
	private static String text = "";
	private static Calendar date = new GregorianCalendar();
	
	private static Logger instance = null;
	
	static {
		if(instance == null){
			instance = new Logger();
		}
	}
	
	private Logger(){

	}
	
	public static void log(String log){
		date.setTime(new Date());
		String dateString = String.format("%02d-%02d %02d:%02d:%02d : ", 
				date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), 
				date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
		text += dateString + log + "\n";
		instance.setChanged();
		instance.notifyObservers(instance);
	}
	
	public static Logger getInstance(){
		return instance;
	}
	
	public String getText(){
		return text;
	}
	
}
