package br.senai.sp.jandira.gamesapp.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Date {

	public static String getDays(LocalDateTime from, LocalDateTime to) {
		
		Duration duration = Duration.between(from, to);
		
		long days = duration.toDays();
		
		return String.valueOf(days + "d");
		
	}
	
}
