package net.com;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Utility { 
	
	public class calender {
		private Integer day;
		private Integer month;
		private Integer year;

		LocalDate currentdate = LocalDate.now();
		public calender() {
			// TO DO Auto-generated constructor stub
		}
		
		public void setDay(Integer day) {
			this.day = currentdate.getDayOfMonth();
		}

		public void setMonth(Integer month) {
			this.month = currentdate.getMonthValue();
		}

		public void setYear(Integer year) {
			this.year = currentdate.getYear();
		}

		public Integer getDay() {
			return day;
		}

		public Integer getMonth() {
			return month;
		}

		public Integer getYear() {
			return year;
		}

		
	}
	
	



	// DD/MM/YYYY
	public String GetDateFormate() {

		LocalDateTime firstOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		LocalDateTime startdDate = firstOfMonth;
		LocalDateTime endDate = firstOfMonth.plusDays(1).minus(1, ChronoUnit.SECONDS);
		Date end = java.util.Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant());
		Date start = java.util.Date.from(startdDate.atZone(ZoneId.systemDefault()).toInstant());
		System.out.println("================ = =========" + start + "=======" + end);
		 
		LocalDate currentdate = LocalDate.now();
		System.out.println("Current date: " + currentdate);
		// Getting the current day 
		// Getting the current month
		Month currentMonth = currentdate.getMonth();
		System.out.println("Current month: " + currentMonth);
		// getting the current year
		int currentYear = currentdate.getYear();
		int month = currentdate.getMonthValue();
		int currentDay = currentdate.getDayOfMonth();
		//2023-03-06 00:00:00.0
		String d=currentYear+"-"+month+"-"+currentDay+" 00:00:00.0";
		return d;
	}
}
