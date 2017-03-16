package im.jahnke.bordcomputer;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
import javax.swing.JPanel;

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
public class MapPanel {
	/**
	 * @param args
	 *            the program args (ignored)
	 */
	
	List<GeoPosition> track = new ArrayList<>();
	JXMapViewer mapViewer;
	
	public JXMapViewer createMapPanel() {
		
		mapViewer = new JXMapViewer();

		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(8);
		mapViewer.setTileFactory(tileFactory);	

		RoutePainter routePainter = new RoutePainter(track);
		mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);
		
		if(track.size()==0){
			GeoPosition gp = new GeoPosition(52.517580, 13.405520);
			track.add(gp);
			mapViewer.setZoom(17);
		}

		// Set the focus
		
		
		mapViewer.addComponentListener(new ComponentAdapter() {			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);
			}
		});

		// Create a compound painter that uses both the route-painter and the
		// waypoint-painter
		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(routePainter);
		// painters.add(waypointPainter);

		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);
		mapViewer.getViewportBounds();

		mapViewer.addKeyListener(new KeyAdapter() {

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
		
		mapViewer.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation()>0) {
					mapViewer.setZoom(mapViewer.getZoom() + 1);
					e.getPoint();
				} else {
					mapViewer.setZoom(mapViewer.getZoom() - 1);
				}
			}
		});
		
		
		MouseAdapter ma = new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				Test.mousePosition = e.getPoint();
				mapViewer.requestFocus();
			}
		};		
		mapViewer.addMouseListener(ma);
		
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
		
		mapViewer.addMouseMotionListener(mma);
		
		return mapViewer;
		
	}
	
	public void addTrackPoint(double latitude, double longitude){
		GeoPosition gp = new GeoPosition(latitude, longitude);
		track.add(gp);
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