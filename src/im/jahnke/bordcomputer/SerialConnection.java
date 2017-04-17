package im.jahnke.bordcomputer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialConnection {
	
	private InputStream in;
	private OutputStream out;
	
	public void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			int timeout = 2000;
			CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				serialPort.setDTR(false);

				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}
	
	public String writeCommand(String command) {
		byte[] cmd = command.getBytes();
		try {
			for (byte b : cmd) {
				this.out.write(b);
			}
		} catch (Exception e) {
			
		}
		
		byte[] buffer = new byte[1024];
		int len = -1;
		boolean running = true;
		String sb = new String("");
		try {
			
			while (running) {
				while ((len = this.in.read(buffer)) > 0) {
					String s = new String(buffer, 0, len);
					sb += s;
					if(sb.contains("===END===")){
						running = false;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	public String[] listSerialPorts() {
		 
	    Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();
	    ArrayList<String> portList = new ArrayList<String>();
	    String portArray[] = null;
	    while (ports.hasMoreElements()) {
	        CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
	        if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
	            portList.add(port.getName());
	        }
	    }
	    portArray = (String[]) portList.toArray(new String[0]);
	    return portArray;
	}

}
