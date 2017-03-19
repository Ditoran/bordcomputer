package im.jahnke.bordcomputer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import im.jahnke.bordcomputer.DeviceManager;
import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.MapPanel;
import im.jahnke.bordcomputer.controller.ActionCommands;
import im.jahnke.bordcomputer.controller.Controller;
import im.jahnke.bordcomputer.model.LogFilesTableModel;

public class Window implements Observer {
	
	JFrame frame;
	JEditorPane logTextPane;
	
	JPanel menuPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	DefaultTableModel tableModel;
	JTable logsTable;
	JScrollPane logsTableScrollPane;
	JTable trackpointTable;
	JScrollPane trackpointTableScrollPane;
	JPanel connectionPanel;
	JPanel logDetailsPanel;
	JPanel connectionPanel2;
	JTabbedPane tabbedPane;
	JPanel logPanel;
	JScrollPane logScrollPane;
	MapPanel mapPanel;
	JSplitPane splitPane;
	
    public Window(Controller controller) {
        frame = new JFrame("BordComputer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        
        frame.add(new MenuPanel(controller), BorderLayout.PAGE_START);
        
        leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(leftPanel, BorderLayout.EAST);
        leftPanel.setLayout(new BorderLayout());
        
        rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(rightPanel, BorderLayout.EAST);
        rightPanel.setLayout(new GridLayout(3, 1));

        String[][] data = {{"1", "24.02.2017", "", "", ""}, {"", "", "", "", ""}};
        String[] logsTableColumns = {"ID", "Datum", "Lat", "Lon", "Open"};
        String[] trackpointTableColumns = {"Datum", "Lat", "Lon"};

        
        tableModel = new DefaultTableModel(logsTableColumns, 0);
        logsTable = new JTable(new LogFilesTableModel());
        
        /*String[] logs = DeviceManager.getDevices();
		Arrays.sort(logs);
		System.out.println(Arrays.toString(logs));

		JComboBox externalDrives = new JComboBox();

		for (String file : logs) {
			tableModel.addRow(new String[]{file.getName()});
		}
		
		String[] logs = DeviceManager.getDevices();
		Arrays.sort(logs);
		System.out.println(Arrays.toString(logs));*/
        
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

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Logger){
			logTextPane.setText(((Logger)arg).getText());
		} else if(arg instanceof JTable){
			
			
		}
	}
}