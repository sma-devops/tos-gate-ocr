package tos.gateocr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

    // Format par défaut pour les dates et heures
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSSS";

    // Méthode pour convertir une chaîne de caractères en LocalDateTime avec un format spécifié
    public static LocalDateTime parseStringToLocalDateTime(String dateString, String format) {
        if (dateString == null || dateString.isEmpty() || format == null || format.isEmpty()) {
            throw new IllegalArgumentException("Date string and format must not be null or empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date string: " + dateString + ". Expected format: " + format, e);
        }
    }

    // Méthode pour formater un LocalDateTime en String avec un format spécifié
    public static String formatLocalDateTimeToString(LocalDateTime dateTime, String format) {
        if (dateTime == null || format == null || format.isEmpty()) {
            throw new IllegalArgumentException("DateTime and format must not be null or empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    // Méthode pour obtenir la différence en secondes entre deux LocalDateTime
    public static long getSecondsDifference(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException("Start and end date time must not be null.");
        }

        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    public static String getDefaultFormat() {
        return DEFAULT_FORMAT;
    }

    // Autres méthodes utilitaires pour manipuler les dates et les heures
    // Par exemple, obtenir la différence en jours, en heures, en minutes, etc.
}
