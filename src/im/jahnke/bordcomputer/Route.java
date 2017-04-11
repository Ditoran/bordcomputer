package im.jahnke.bordcomputer;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Route {
	
	private int id;
	private File file;
	
	private ArrayList<TrackPoint> trackPoints = new ArrayList<>();
	
	
	public Route(File file) {
		this.file = file;
		this.id = Integer.parseInt(file.getName().split("\\.")[0]);
		parse();
	}
	
	public ArrayList<TrackPoint> getTrackPoints(){
		return trackPoints;
	}
	
	public int getID(){
		return id;
	}
	
	public Date getStartDate(){
		if(trackPoints.size()!=0){
			return trackPoints.get(0).getDate();
		}
		return null;		
	}
	
	public Date getEndDate(){
		if(trackPoints.size()!=0){
			return trackPoints.get(trackPoints.size()-1).getDate();
		}
		return null;		
	}
	
	public TrackPoint getStartPoint(){
		if(trackPoints.size()!=0){
			return trackPoints.get(0);
		}
		return null;		
	}
	
	public TrackPoint getEndPoint(){
		if(trackPoints.size()!=0){
			return trackPoints.get(trackPoints.size()-1);
		}
		return null;		
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
			double latitude = Double.parseDouble(line[0]);
			double longitude = Double.parseDouble(line[1]);
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	        Date date = formatter.parse(line[2]);
			TrackPoint tp = new TrackPoint(latitude, longitude, date);
			trackPoints.add(tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return String.format("ID: %d, %s, %s, %s, %s, %f", id, getStartDate(), getEndDate(), getStartPoint(), getEndPoint(), getDistance());
	}

}
