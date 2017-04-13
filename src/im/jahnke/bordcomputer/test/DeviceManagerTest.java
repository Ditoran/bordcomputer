package im.jahnke.bordcomputer.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import im.jahnke.bordcomputer.util.DeviceManager;

public class DeviceManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDevices() {
		String test[] = DeviceManager.getDevices();
		System.out.println(Arrays.toString(test));
		assertEquals(4, test.length);
	}

	@Test
	public void testSetDefaultDevice() {
		DeviceManager.setDefaultDevice("D:\\");
		assertEquals(DeviceManager.getDefaultDevice(), "D:\\");
	}
	
	@Test
	public void testFindDevice(){
		String device = DeviceManager.findDevice();
		assertEquals("F:\\", device);
	}

}
