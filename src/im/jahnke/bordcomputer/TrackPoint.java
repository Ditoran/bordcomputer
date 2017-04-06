package im.jahnke.bordcomputer;

import java.util.Date;

public class TrackPoint {
	
	private double latitude;
	private double longitude;
	private Date date;
	
	public TrackPoint(double latitude, double longitude, Date date) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Date getDate() {
		return date;
	}
	
	/**
	 * 
	 * @param tp1
	 * @param tp2
	 * @return Distance in KM
	 */
	public static double distanceBetweenTrackPoints(TrackPoint tp1, TrackPoint tp2){
		double d =  3959 * Math.acos( Math.cos( Math.toRadians(tp2.getLatitude()) ) 
							* Math.cos( Math.toRadians( tp1.getLatitude() ) ) 
							* Math.cos( Math.toRadians( tp1.getLongitude() ) - Math.toRadians(tp2.getLongitude()) )
							+ Math.sin( Math.toRadians(tp2.getLatitude()) ) 
							* Math.sin( Math.toRadians( tp1.getLatitude() ) ) ) ;
		return d;		
	}

}
