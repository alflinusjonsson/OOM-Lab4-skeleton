package se.ju.csi.oom.lab4;

import java.util.Arrays;
import java.util.HashSet;

public class TimeZoneTranslator {

	static DateTime shiftTimeZone(DateTime inputDate, int fromOffset, int toOffset) {

		int inputHour = inputDate.getHour();
		int gmtHour = inputHour - fromOffset;
		int targetHour = gmtHour + toOffset;

		int day = inputDate.getDay();
		int month = inputDate.getMonth();
		int year = inputDate.getYear();

		if (targetHour < 0) {
			targetHour+=24;
			day--;
		}
		
		if (targetHour > 24) {
			targetHour-=24;
			day++;
		}
		
		if (day < 1) {
			day+=31;
			month--;
		}

		if (day > 31) {
			day-=31;
			month++;
		}

		if (month < 1) {
			month+=12;
			year--;
		}

		if (month > 12) {
			month-=12;
			year++;
		}
		
		DateTime targetDateTime = new DateTime(year, month, day, targetHour, inputDate.getMinute() );

		return targetDateTime;
	}

	static Event shiftEventTimeZone(Event inputEvent, TimeZone fromZone, TimeZone toZone) {
		DateTime shiftedStart = shiftTimeZone(inputEvent.getStartDate(), fromZone.getOffset(), toZone.getOffset());
		DateTime shiftedEnd = shiftTimeZone(inputEvent.getEndDate(), fromZone.getOffset(), toZone.getOffset());
		Event targetEvent = new Event(inputEvent.getLabel(), shiftedStart, shiftedEnd, inputEvent.getAttendees(),
				inputEvent.getLocation());
		return targetEvent;
	}

	public static void main(String[] args) {
		DateTime LectureStart = new DateTime(2018, 8, 27, 8, 0);
		DateTime LectureEnd = new DateTime(2018, 8, 27, 9, 45);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218", 57.7785672, 14.1614833, 20.0);

		Event firstOomLecture = new Event("OOM 2018 Lecture 1", LectureStart, LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)), HC218);

		System.out.println(String.format("============\nOriginal event\n============\n%s", firstOomLecture.toString()));
		System.out.println();
		System.out.println(String.format(
				"========================\nEvent shifted to Boston time\n========================\n%s",
				shiftEventTimeZone(firstOomLecture, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.US_EASTERN).toString()));
	}
}
