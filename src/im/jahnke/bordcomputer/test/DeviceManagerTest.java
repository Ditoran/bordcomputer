package im.jahnke.bordcomputer.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import im.jahnke.bordcomputer.DeviceManager;

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
		assertEquals(3, test.length);
	}

	@Test
	public void testSetDefaultDevice() {
		DeviceManager.setDefaultDevice("D:\\");
		assertEquals(DeviceManager.getDefaultDevice(), "D:\\");
	}

}
