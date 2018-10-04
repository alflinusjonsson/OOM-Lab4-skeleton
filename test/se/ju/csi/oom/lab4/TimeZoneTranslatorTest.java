package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShiftTimeZone() {
		
		DateTime datetime = new DateTime(2018,10,4,12,0,0);
		datetime = TimeZoneTranslator.shiftTimeZone(datetime, TimeZone.GREENWICH_UTC.getOffset(), TimeZone.PAKISTAN.getOffset());
		
		assertEquals("2018-10-04 17:00:00", datetime.toString() );
	}

	@Test
	public void testShiftEventTimeZone() {
		
		
		DateTime LectureStart = new DateTime(2018, 10, 4, 13, 0, 0);
		DateTime LectureEnd = new DateTime(2018, 10, 4, 14, 45, 0);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218",57.7785672,14.1614833,20.0);
		
		Event firstOomLecture = new Event("OOM 2018 Lecture 1",
				LectureStart,
				LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)),
				HC218);
		
		firstOomLecture = TimeZoneTranslator.shiftEventTimeZone(firstOomLecture, TimeZone.GREENWICH_UTC, TimeZone.PAKISTAN);
		
		assertEquals("2018-10-04 18:00:00", firstOomLecture.getStartDate().toString() );
		assertEquals("2018-10-04 19:45:00", firstOomLecture.getEndDate().toString() );
		
	}
	
	@Test
	public void testShiftTimeZone2() {
		
		DateTime datetime = new DateTime(2016, 1, 1, 6, 0, 0);
		datetime = TimeZoneTranslator.shiftTimeZone(datetime, TimeZone.CENTRAL_EUROPEAN_TIME.getOffset(), TimeZone.US_PACIFIC.getOffset());
		
		assertEquals("2015-12-31 21:00:00", datetime.toString() );
		
	}
	
	
}
