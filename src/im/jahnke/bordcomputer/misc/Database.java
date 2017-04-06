package im.jahnke.bordcomputer.misc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import org.jxmapviewer.viewer.GeoPosition;

public class Database {
	
	private Connection c = null;
	
	public Database() {		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:data.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		//test();
	}
	
	public Connection getConnection(){
		return c;
	}
	
	private void test(){
		try {
			File f = new File("res/2.txt");
			Scanner sc = new Scanner(f);
			c.setAutoCommit(false);
			while (sc.hasNextLine()) {
				String[] s = sc.nextLine().split(";");
				GeoPosition gp = new GeoPosition(Double.parseDouble(s[1]), Double.parseDouble(s[2]));
				Statement stmt = c.createStatement();
				String sql = "INSERT INTO trackpoint (route_id,latitude,longitude,date,mileage) " +
				   "VALUES (" + s[0] + ", '" + s[1] + "', ' " + s[2] + " ', '" + s[3] + "', 0 );"; 
				stmt.executeUpdate(sql);
				stmt.close();
				//c.commit();
			}
			c.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}