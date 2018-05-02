package com;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class CountSunday {

	public static void main(String[] args) {
		int	count=0;
		for(int	year=1901;year<=2000;year++) {
			for(int	month=1;month<=12;month++) {
				if(LocalDate.of(year, month, 1).getDayOfWeek()==DayOfWeek.SUNDAY)
				count++;
			}
			
		}
		System.out.println("number	of	sundays	fell	on	the	first	of	the	month	duringthe	20th	century"+count);
	}

}
