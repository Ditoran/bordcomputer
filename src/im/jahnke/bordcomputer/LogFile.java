package im.jahnke.bordcomputer;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LogFile {
	
	private int id;
	private File file;
	
	private ArrayList<TrackPoint> trackPoints = new ArrayList<>();
	
	
	public LogFile(File file) {
		this.file = file;
		parse();
	}
	
	public ArrayList<TrackPoint> getTrackPoints(){
		return trackPoints;
	}
	
	public double getDistance(){
		double distance = 0;
		for(int i=0; i<trackPoints.size()-1; i++){
			TrackPoint tp1 = trackPoints.get(i);
			TrackPoint tp2 = trackPoints.get(i+1);
			distance += TrackPoint.distanceBetweenTrackPoints(tp1, tp2);
		}
		return distance;
	}
	
	
	
	private void parse(){
		try (	
			FileInputStream fis = new FileInputStream(file);
			Scanner sc = new Scanner(fis);
		){
			String line;
			String[] splittedLine;
			while(sc.hasNextLine()){
				line = sc.nextLine();
				splittedLine = line.split(";");
				parseLine(splittedLine);
			}
		} catch (Exception e) {
			
		}
	}
	
	private void parseLine(String[] line){
		try {
			double latitude = Double.parseDouble(line[1]);
			double longitude = Double.parseDouble(line[2]);
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	        Date date = formatter.parse(line[3]);
			TrackPoint tp = new TrackPoint(latitude, longitude, date);
			trackPoints.add(tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
