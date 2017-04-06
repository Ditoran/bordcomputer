package im.jahnke.bordcomputer.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import im.jahnke.bordcomputer.LogFile;

public class LogFileTest {
	
	static LogFile file;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File f = new File("tst\\res\\LogExample.txt");
		file = new LogFile(f);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseFileTest() {
		
		assertEquals(5, file.getTrackPoints().size());
	}
	
	@Test
	public void distanceTest(){
		double expected = 414.48926968864214;
		double actual = file.getDistance();
		double delta = 0.001;
		boolean flag = Math.abs(expected - actual) < delta;
		assertTrue(flag);
	}

}
