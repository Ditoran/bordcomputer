package im.jahnke.bordcomputer;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;

/**
 * A simple sample application that shows a OSM map of Europe containing a route
 * with waypoints
 * 
 * @author Martin Steiger
 */
public class Sample {
	/**
	 * @param args
	 *            the program args (ignored)
	 */
	
	
	
	public static <T> void main(String[] args) {
		
		Point mousePosition = new Point(0,0);
		JXMapViewer mapViewer = new JXMapViewer();

		// Display the viewer in a JFrame
		JFrame frame = new JFrame("JXMapviewer2 Example 2");
		frame.getContentPane().add(mapViewer);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(8);
		mapViewer.setTileFactory(tileFactory);

		List<GeoPosition> track = new ArrayList<GeoPosition>();

		try {
			File f = new File("res/2.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] s = sc.nextLine().split(";");
				GeoPosition gp = new GeoPosition(Double.parseDouble(s[1]), Double.parseDouble(s[2]));
				System.out.println(mapViewer.convertGeoPositionToPoint(gp));
				track.add(gp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create a track from the geo-positions

		RoutePainter routePainter = new RoutePainter(track);

		// Set the focus
		mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);
		System.out.println("mapViewer.getZoom(): " + mapViewer.getZoom());
		System.out.println("viewport: " + mapViewer.getViewportBounds().toString());

		// Create a compound painter that uses both the route-painter and the
		// waypoint-painter
		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(routePainter);
		// painters.add(waypointPainter);

		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);
		mapViewer.getViewportBounds();

		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					mapViewer.setCenter(
							new Point.Double(mapViewer.getCenter().getX(), mapViewer.getCenter().getY() - 100));
					break;
				case KeyEvent.VK_A:
					mapViewer.setCenter(
							new Point.Double(mapViewer.getCenter().getX() - 100, mapViewer.getCenter().getY()));
					break;
				case KeyEvent.VK_S:
					mapViewer.setCenter(
							new Point.Double(mapViewer.getCenter().getX(), mapViewer.getCenter().getY() + 100));
					break;
				case KeyEvent.VK_D:
					mapViewer.setCenter(
							new Point.Double(mapViewer.getCenter().getX() + 100, mapViewer.getCenter().getY()));
					break;
				case KeyEvent.VK_PLUS:
					mapViewer.setZoom(mapViewer.getZoom() - 1);
					break;
				case KeyEvent.VK_MINUS:
					mapViewer.setZoom(mapViewer.getZoom() + 1);
					break;
				default:
					break;
				}
			}
		});
		
		frame.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation()>0) {
					System.out.println("Down");
					mapViewer.setZoom(mapViewer.getZoom() + 1);
					e.getPoint();
				} else {
					System.out.println("Up");
					mapViewer.setZoom(mapViewer.getZoom() - 1);
				}
			}
		});
		
		
		MouseAdapter ma = new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				Test.mousePosition = e.getPoint();
			}
		};		
		frame.addMouseListener(ma);
		
		MouseMotionAdapter mma = new MouseMotionAdapter() {
		
			@Override
			public void mouseDragged(MouseEvent e) {
				
				System.out.println(e.getPoint().toString());
				int diffX = Test.mousePosition.x - e.getPoint().x;
				int diffY = Test.mousePosition.y - e.getPoint().y;
				System.out.printf("x: %d, y: %d\n", diffX, diffY);
				mapViewer.setCenter(new Point.Double(mapViewer.getCenter().getX()+diffX, mapViewer.getCenter().getY()+diffY));
				Test.mousePosition = e.getPoint();
			}
		};
		
		frame.addMouseMotionListener(mma);
		
		
	}
	
	
}

class Test{
	static Point mousePosition = new Point();
	
	public static Point getPosition(){
		return mousePosition;
	}
	
	public static void setPoint(Point p){
		mousePosition = p;
	}
}