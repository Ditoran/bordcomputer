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
import javax.swing.table.DefaultTableModel;
import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.MapPanel;
import im.jahnke.bordcomputer.Route;
import im.jahnke.bordcomputer.controller.MainController;
import im.jahnke.bordcomputer.controller.LogFilesTableController;
import im.jahnke.bordcomputer.controller.MenuController;

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
	private MenuPanel menuPanel;
	
	private MainController controller;
	LogFilesTableController lftc;
	
    public MainWindow() {
        frame = new JFrame("BordComputer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        
        controller = new MainController(this);
        
        Logger.getInstance().addObserver(this);
        
        menuPanel = new MenuPanel(controller);
        
        frame.add(menuPanel, BorderLayout.PAGE_START);
        
        leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(leftPanel, BorderLayout.EAST);
        leftPanel.setLayout(new BorderLayout());
        
        rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(rightPanel, BorderLayout.EAST);
        rightPanel.setLayout(new GridLayout(3, 1));

        String[][] data = {{"1", "24.02.2017", "", "", ""}, {"", "", "", "", ""}};
        String[] trackpointTableColumns = {"Datum", "Lat", "Lon"};
        
        lftc = new LogFilesTableController(controller);
        logsTable = lftc.getView();
        logsTable.setValueAt("test", 0, 0);
        
        logsTable.addMouseListener(controller);
        logsTable.setName("test");
        
        logsTableScrollPane = new JScrollPane(logsTable);
        
        trackpointTable = new JTable(data, trackpointTableColumns);
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
        
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        
        //leftPanel.add(mapPanel, BorderLayout.NORTH);
        mapPanel = new MapPanel();
                
        try {
			File f = new File("res/12.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] s = sc.nextLine().split(";");
				mapPanel.addTrackPoint(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //leftPanel.add(mapPanel.createMapPanel(), BorderLayout.CENTER);
        leftPanel.add(logPanel, BorderLayout.SOUTH);
        
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
    
    public void addRoute(Route route) {
		lftc.addRoute(route);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Logger){
			logTextPane.setText(((Logger)arg).getText());
		} else if(arg instanceof JTable){
			
			
		}
	}
}