package tos.gateocr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

	// Format par défaut pour les dates et heures
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSSS";

	// Méthode pour convertir une chaîne de caractères en LocalDateTime avec un
	// format spécifié
	public static LocalDateTime parseStringToLocalDateTime(String dateString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(dateString, formatter);
	}

	// Méthode pour formater un LocalDateTime en String avec un format spécifié
	public static String formatLocalDateTimeToString(LocalDateTime dateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return dateTime.format(formatter);
	}

	// Méthode pour obtenir la différence en secondes entre deux LocalDateTime
	public static long getSecondsDifference(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
	}

	public static String getDefaultFormat() {
		return DEFAULT_FORMAT;
	}

	// Autres méthodes utilitaires pour manipuler les dates et les heures
	// Par exemple, obtenir la différence en jours, en heures, en minutes, etc.
}
