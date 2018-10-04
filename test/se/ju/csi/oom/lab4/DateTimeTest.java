package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTimeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		
		DateTime dateTime = new DateTime(2018, 1, 1, 12, 0, 0);
		assertEquals("2018-01-01 12:00:00", dateTime.toString() );
	}
	
	@Test
	public void testDateTimeString() {
		
		DateTime dateTimeString = new DateTime("2018-01-01 12:00:00");
		assertEquals("2018-01-01 12:00:00", dateTimeString.toString() );
	}

}
