package im.jahnke.bordcomputer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;

import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.MapPanel;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.controller.LogFilesTableController;
import im.jahnke.bordcomputer.controller.MainController;
import im.jahnke.bordcomputer.controller.TrackPointTableController;

public class MainWindow implements Observer {
	
	private JFrame frame;
	private JEditorPane logTextPane;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTable logsTable;
	private JScrollPane logsTableScrollPane;
	private JTable trackpointTable;
	private JScrollPane trackpointTableScrollPane;
	private JPanel connectionPanel;
	private JPanel logDetailsPanel;
	private JPanel connectionPanel2;
	private JTabbedPane tabbedPane;
	private JPanel logPanel;
	private JScrollPane logScrollPane;
	private MapPanel mapPanel;
	private JSplitPane splitPane;
	private MenuBar menuPanel;
	
	LogFilesTableController lftc;
	TrackPointTableController tptc;
	
    public MainWindow() {
        frame = new JFrame("BordComputer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
               
        Logger.getInstance().addObserver(this);
        
        menuPanel = new MenuBar();
        
        frame.add(menuPanel, BorderLayout.PAGE_START);
        
        leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(leftPanel, BorderLayout.EAST);
        leftPanel.setLayout(new BorderLayout());
        
        rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(rightPanel, BorderLayout.EAST);
        rightPanel.setLayout(new GridLayout(3, 1));
        
        lftc = new LogFilesTableController();
        logsTable = lftc.getView();                
        logsTableScrollPane = new JScrollPane(logsTable);
        
        tptc = new TrackPointTableController();
        trackpointTable = tptc.getView();
        trackpointTableScrollPane = new JScrollPane(trackpointTable);
        
        connectionPanel = new JPanel();
        connectionPanel.setBackground(Color.DARK_GRAY);
        
        logDetailsPanel = new JPanel();
        logDetailsPanel.setBackground(Color.DARK_GRAY);
        
        connectionPanel2 = new JPanel();
        connectionPanel2.setBackground(Color.DARK_GRAY);
        
        
        tabbedPane = new JTabbedPane();


        tabbedPane.addTab("Routendetails", logDetailsPanel);
        tabbedPane.addTab("Trackpoints", trackpointTableScrollPane);
        
        logPanel = new JPanel();
        logPanel.setBackground(Color.red);
        logPanel.setPreferredSize(new Dimension(logPanel.getPreferredSize().width, 200));
        logPanel.setLayout(new BorderLayout());
        
        logTextPane = new JEditorPane();
        logTextPane.setEditable(false);
        logTextPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logScrollPane = new JScrollPane(logTextPane);
        logScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //logScrollPane.setBorder(new EmptyBorder( 5, 0, 0, 0 ));
        
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        
        mapPanel = new MapPanel();
        JXMapViewer viewer = mapPanel.initMapPanel();
        viewer.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        leftPanel.add(viewer, BorderLayout.CENTER);
        
        leftPanel.add(logPanel, BorderLayout.PAGE_END);
        
        rightPanel.add(logsTableScrollPane);
        rightPanel.add(tabbedPane);
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				splitPane.setDividerLocation(frame.getSize().width/5*3);
				//mapPanel.setMinimumSize(new Dimension(800, 600));
				//mapPanel.setBackground(Color.orange);
			}
		});
        frame.add(splitPane, BorderLayout.CENTER);
        
        JPanel statusBar = new JPanel();
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
        
        statusBar.setBackground(Color.green);
        JLabel leftStatus = new JLabel("test");
        statusBar.add(leftStatus);
        statusBar.add(Box.createHorizontalGlue());

        JLabel rightStatus = new JLabel("test2");
        statusBar.add(rightStatus);
        
        frame.add(statusBar, BorderLayout.PAGE_END);
        
        frame.setVisible(true);
        frame.pack();
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }
    
	public LogFilesTableController getLogFilesTable() {
		return lftc;
	}
	
	public TrackPointTableController getTrackPointTable() {
		return tptc;
	}

	public MenuBar getMenuPanel() {
		return menuPanel;
	}
	
	public MapPanel getMapPanel(){
		return mapPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Logger){
			logTextPane.setText(((Logger)arg).getText());
		} else if(arg instanceof JTable){
			
			
		}
	}
}