package im.jahnke.bordcomputer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import im.jahnke.bordcomputer.DeviceManager;
import im.jahnke.bordcomputer.Logger;
import im.jahnke.bordcomputer.MapPanel;
import im.jahnke.bordcomputer.controller.Controller;

public class Window implements Observer {
	
	JFrame frame;
	JEditorPane logTextPane;
	JToolBar toolBar;
	JButton button;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem item;
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
        
        toolBar = new JToolBar("Still draggable");
        
        toolBar.setPreferredSize(new Dimension(toolBar.getSize().width, 32));
        frame.add(toolBar, BorderLayout.PAGE_START);
        
        button = new JButton();
        button.setIcon(new ImageIcon("icons/usb.png"));
        button.setActionCommand("usb");
        button.addActionListener(controller);
        
        toolBar.add(button);
        
        menuBar = new JMenuBar();
        
        menu = new JMenu("Datei");
        
        item = new JMenuItem("Öffnen");
        
        menu.add(item);
        
        menuBar.add(menu);
        
        //test.add(menuBar, BorderLayout.BEFORE_FIRST_LINE);
        
        
        leftPanel = new JPanel();
        frame.add(leftPanel, BorderLayout.EAST);
        leftPanel.setLayout(new BorderLayout());
        
        rightPanel = new JPanel();
        frame.add(rightPanel, BorderLayout.EAST);
        rightPanel.setLayout(new GridLayout(3, 1));

        String[][] data = {{"1", "24.02.2017", "", "", ""}, {"", "", "", "", ""}};
        String[] logsTableColumns = {"ID", "Datum", "Lat", "Lon", "Open"};
        String[] trackpointTableColumns = {"Datum", "Lat", "Lon"};

        
        tableModel = new DefaultTableModel(logsTableColumns, 0);
        logsTable = new JTable(tableModel);
        
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
        
        /*JComboBox<String> externalDrives = new JComboBox<String>();
        
        JPanel panel = new JPanel();
        panel.add(externalDrives);
        
        JOptionPane.showMessageDialog(null, panel);
        
        
        
        if(externalDrives.getSelectedItem() != null){
        	
        } else {
        	System.exit(0);
        }

		

		String[] logs = DeviceManager.getDevices();
		Arrays.sort(logs);
		for (String device : logs) {
			externalDrives.addItem(device);
		}*/
		
		/*logsTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				int row = logsTable.rowAtPoint(e.getPoint());
				int column = logsTable.columnAtPoint(e.getPoint());
				logsTable.addColumnSelectionInterval(0, tableModel.getColumnCount()-1);
			}
		});*/
        
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
        //logTextPane.setEditable(false);
        logTextPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logTextPane.setText("Hallo Welt\n");
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
        
        leftPanel.add(mapPanel.createMapPanel(), BorderLayout.CENTER);
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
        frame.add(splitPane);
        
        frame.setVisible(true);
        frame.pack();
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //mapPanel.setMinimumSize(new Dimension(800, 600));

        //splitPane.setDividerLocation(test.getWidth()/3*2);
        Logger.log("Test");
        Logger.log("Test1");
        Logger.log("Test2");
        Logger.log("Test3");

    }

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Logger){
			logTextPane.setText(((Logger)arg).getText());
		}
	}
}