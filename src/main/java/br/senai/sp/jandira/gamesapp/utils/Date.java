package br.senai.sp.jandira.gamesapp.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Date {

	public static String getDays(LocalDateTime from, LocalDateTime to) {
		Duration duration = Duration.between(from, to);
		long days = duration.toDays();
	
		return String.valueOf(days + "d");
	}
	
	public static LocalDateTime getDateTime() {
		//ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
		//LocalDateTime now = LocalDateTime.now(zoneId);
		LocalDateTime now = LocalDateTime.now();
		return now;
	}
	
}
