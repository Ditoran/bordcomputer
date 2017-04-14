package im.jahnke.bordcomputer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;

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
	
	public JXMapViewer initMapPanel() {
		
		mapViewer = new JXMapViewer();
		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(8);
		mapViewer.setTileFactory(tileFactory);	

		RoutePainter routePainter = new RoutePainter(track);
		mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.9);
		
		if(track.size()==0){
			GeoPosition gp = new GeoPosition(52.517580, 13.405520);
			track.add(gp);
			mapViewer.setZoom(17);
		}
		// Create a compound painter that uses both the route-painter and the
		// waypoint-painter
		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(routePainter);
		// painters.add(waypointPainter);

		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);
		mapViewer.getViewportBounds();

		addListeners();
		
		return mapViewer;
		
	}
	
	public void fillMapPanel(Route route){
		track.clear();
		for (TrackPoint tp : route.getTrackPoints()) {
			addTrackPoint(tp.getLatitude(), tp.getLongitude());
		}
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

		// Create a compound painter that uses both the route-painter and the
		// waypoint-painter
		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(routePainter);

		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);
		mapViewer.getViewportBounds();
	}
	
	private void addTrackPoint(double latitude, double longitude){
		GeoPosition gp = new GeoPosition(latitude, longitude);
		track.add(gp);
	}
	
	private void addListeners(){
		mapViewer.addComponentListener(new ComponentAdapter() {			
			@Override
			public void componentResized(ComponentEvent e) {
				mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 0.7);
			}
		});		
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
				Point current = e.getPoint();
				Rectangle bound = mapViewer.getViewportBounds();
				
				double dx = current.x - bound.width / 2;
				double dy = current.y - bound.height / 2;
				
				Dimension oldMapSize = mapViewer.getTileFactory().getMapSize(mapViewer.getZoom());

				mapViewer.setZoom(mapViewer.getZoom() + e.getWheelRotation());
				
				Dimension mapSize = mapViewer.getTileFactory().getMapSize(mapViewer.getZoom());

				Point2D center = mapViewer.getCenter();

				double dzw = (mapSize.getWidth() / oldMapSize.getWidth());
				double dzh = (mapSize.getHeight() / oldMapSize.getHeight());

				double x = center.getX() + dx * (dzw - 1);
				double y = center.getY() + dy * (dzh - 1);

				mapViewer.setCenter(new Point2D.Double(x, y));
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
				int diffX = Test.mousePosition.x - e.getPoint().x;
				int diffY = Test.mousePosition.y - e.getPoint().y;
				System.out.printf("x: %d, y: %d\n", diffX, diffY);
				mapViewer.setCenter(new Point.Double(mapViewer.getCenter().getX()+diffX, mapViewer.getCenter().getY()+diffY));
				Test.mousePosition = e.getPoint();
			}
		};
		mapViewer.addMouseMotionListener(mma);
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