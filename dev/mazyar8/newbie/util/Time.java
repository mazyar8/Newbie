package dev.mazyar8.newbie.util;

public class Time {
	
	public static long calculateTimeDifferenceByMinute(long time0, long time1) {
		long delta = time0 - time1;
		long hours = delta / (60 * 1000) % 60;
		return hours;
	}

	public static long calculateTimeDifferenceByHour(long time0, long time1) {
		long delta = time0 - time1;
		long hours = delta / (60 * 60 * 1000) % 24;
		return hours;
	}
	
	public static long getElapsedTimeByMinute(long time) {
		return calculateTimeDifferenceByMinute(System.currentTimeMillis(), time);
	}
	
	public static long getElapsedTimeByHour(long time) {
		return calculateTimeDifferenceByHour(System.currentTimeMillis(), time);
	}
	
}
