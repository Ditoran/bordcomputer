package im.jahnke.bordcomputer.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import im.jahnke.bordcomputer.Route;

public class LogFileTest {
	
	static Route file1;
	static Route file2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File f1 = new File("tst\\res\\LogExample.txt");
		file1 = new Route(f1);
		
		File f2 = new File("tst\\res\\3.txt");
		file2 = new Route(f2);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseFileTest() {
		
		assertEquals(809, file1.getTrackPoints().size());
	}
	
	@Test
	public void distanceTest(){
		double expected = 414.48926968864214;
		double actual = file1.getDistance();
		double delta = 0.001;
		boolean flag = Math.abs(expected - actual) < delta;
		System.out.println(actual);
		assertTrue(flag);
	}

}
