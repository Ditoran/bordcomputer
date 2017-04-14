package im.jahnke.bordcomputer;

import java.util.Date;

public class TrackPoint {
	
	private double latitude;
	private double longitude;
	private int altitude;
	private Date date;
	private int speed;
	
	public TrackPoint(double latitude, double longitude, int altitude, Date date, int speed) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.date = date;
		this.speed = speed;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public int getAltitude(){
		return altitude;
	}

	public Date getDate() {
		return date;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * 
	 * @param tp1
	 * @param tp2
	 * @return Distance in KM
	 */
	public static double distanceBetweenTrackPoints(TrackPoint tp1, TrackPoint tp2){
		double d =  6371 * Math.acos( Math.cos( Math.toRadians(tp2.getLatitude()) ) 
							* Math.cos( Math.toRadians( tp1.getLatitude() ) ) 
							* Math.cos( Math.toRadians( tp1.getLongitude() ) - Math.toRadians(tp2.getLongitude()) )
							+ Math.sin( Math.toRadians(tp2.getLatitude()) ) 
							* Math.sin( Math.toRadians( tp1.getLatitude() ) ) ) ;
		return d;		
	}

}
