package im.jahnke.bordcomputer.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JSpinner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GoogleMapsApi {
	
	private static final String API_KEY = "AIzaSyCdAlnmoR5w_JjREZDLz88YIOgAz1bTGfU";
	
	public static String coordinatesToCity(double latitude, double longitude) throws Exception{
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?"
				+ "latlng=" + Double.toString(latitude) + ","
				+ Double.toString(longitude) + "&key=" + API_KEY);
		
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(response.toString());
		JSONArray arr = (JSONArray)object.get("results");
		object = (JSONObject)arr.get(2);
		arr = (JSONArray)object.get("address_components");
		object = (JSONObject)arr.get(0);
		System.out.println(object.get("long_name"));
		
		return object.toString();
	}

}
