package im.jahnke.bordcomputer;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;

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
	
	public double getArea(){
		double area = 0;
		
		
		return area;
	}
	
	public double getDuration(){
		double duration = (getEndDate().getTime() - getStartDate().getTime())/(1000.0*3600.0);
		return duration;
	}
	
	public double getDrivingTime(){
		double drivingTime = 0;
		for (int i = 1; i < trackPoints.size(); i++) {
			TrackPoint tp1 = trackPoints.get(i-1);
			TrackPoint tp2 = trackPoints.get(i);
			if(tp2.getSpeed() != 0){
				drivingTime += (tp2.getDate().getTime() - tp1.getDate().getTime());
			}
		}		
		
		return drivingTime/(1000.0*3600.0);
	}
	
	public double getStandStillTime(){
		double standStillTime = 0;
		for (int i = 1; i < trackPoints.size(); i++) {
			TrackPoint tp1 = trackPoints.get(i-1);
			TrackPoint tp2 = trackPoints.get(i);
			if(tp2.getSpeed() == 0){
				standStillTime += (tp2.getDate().getTime() - tp1.getDate().getTime());
			}
		}		
		
		return standStillTime/(1000.0*3600.0);
	}
	
	public int getAverageSpeed(){
		double averageSpeed = 0;
		for (TrackPoint trackPoint : trackPoints) {
			averageSpeed += trackPoint.getSpeed();
		}
		averageSpeed = averageSpeed / trackPoints.size();
		
		return (int)averageSpeed;
	}
	
	public int getAverageDrivingSpeed(){
		double averageDrivingSpeed = 0;
		for (TrackPoint trackPoint : trackPoints) {
			averageDrivingSpeed += trackPoint.getSpeed();
		}
		averageDrivingSpeed = averageDrivingSpeed / trackPoints.size();
		
		return (int)averageDrivingSpeed;
	}
	
	public int getMinSpeed(){
		double min = Double.MAX_VALUE;
		for (TrackPoint trackPoint : trackPoints) {
			if(trackPoint.getSpeed()<min){
				min = trackPoint.getSpeed();
			}
		}
		return (int)min;
	}
	
	public int getMaxSpeed(){
		double max = Integer.MIN_VALUE;
		for (TrackPoint trackPoint : trackPoints) {
			if(trackPoint.getSpeed()>max){
				max = trackPoint.getSpeed();
			}
		}
		return (int)max;
	}
	

	public int getMinHeight(){
		int min = Integer.MAX_VALUE;
		for (TrackPoint trackPoint : trackPoints) {
			if(trackPoint.getAltitude()<min){
				min = trackPoint.getAltitude();
			}
		}
		return min;
	}
	
	public int getMaxHeight(){
		int max = Integer.MIN_VALUE;
		for (TrackPoint trackPoint : trackPoints) {
			if(trackPoint.getAltitude()>max){
				max = trackPoint.getAltitude();
			}
		}
		return max;
	}
	
	public int getElevationUpHill(){
		int elevationUpHill = 0;
		for (int i = 1; i < trackPoints.size(); i++) {
			TrackPoint tp1 = trackPoints.get(i-1);
			TrackPoint tp2 = trackPoints.get(i);
			if(tp2.getAltitude()-tp1.getAltitude()>0){
				elevationUpHill += tp2.getAltitude()-tp1.getAltitude();
			}
		}
		return elevationUpHill;
	}
	
	public int getElevationDownHill(){
		int elevationDownHill = 0;
		for (int i = 1; i < trackPoints.size(); i++) {
			TrackPoint tp1 = trackPoints.get(i-1);
			TrackPoint tp2 = trackPoints.get(i);
			if(tp2.getAltitude()-tp1.getAltitude()<0){
				elevationDownHill += tp1.getAltitude()-tp2.getAltitude();
			}
		}
		return elevationDownHill;
	}
	
	public double getAverageFuel(){
		return 0;
	}
	
	public double getMinFuel(){
		return 0;
	}
	
	public double getMaxFuel(){
		return 0;
	}
	
	public double getFuelVolume(){
		return 0;
	}
	
	public double getAverageOuterTemperature(){
		return 0;
	}
	
	public double getMinOuterTemperature(){
		return 0;
	}
	
	public double getMaxOuterTemperature(){
		return 0;
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
			TrackPoint tp = new TrackPoint(latitude, longitude, 0, date, 0); //TODO:
			trackPoints.add(tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return String.format("ID: %d, %s, %s, %s, %s, %f", id, getStartDate(), getEndDate(), getStartPoint(), getEndPoint(), getDistance());
	}

}
