package fr.m2iformation.authentification.utils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.google.common.hash.Hashing;

public class JpaTools {
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	/**
	 * @param word
	 * @return word <==> Word
	 */
	public static String capitalize(String word) {
		if (!isNullOrWithSpace(word)) {
			return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param localDate
	 * @return LocalDate ==> Date
	 */
	public static Date asDate(LocalDate localDate) {
		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	/**
	 *
	 * @param date
	 * @return Date ==> LocalDate
	 */
	public static LocalDate asLocalDate(Date date) {
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 *
	 * @param dateString
	 * @return String ==> LocalDate
	 */
	public static LocalDate parse(String dateString) {
		try {
			return LocalDate.parse(dateString, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 *
	 * @param dateString
	 * @return
	 */
	public static boolean validDate(String dateString) {
		return parse(dateString) != null;
	}

	/**
	 *
	 * @param date
	 * @return LocalDate ==> String
	 */
	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}

	/**
	 * Calcule un age � partir d'une date utile
	 *
	 * @param ddn
	 * @return
	 */
	public static int calculAge(Date ddn) {
		LocalDate naissance = asLocalDate(ddn);
		LocalDate maintenant = LocalDate.now();
		Period period = Period.between(naissance, maintenant);
		return period.getYears();
	}

	/**
	 * Calcule un age � partir d'une LocalDate
	 *
	 * @param ddn
	 * @return
	 */
	public static int calculAge(LocalDate ddn) {
		LocalDate maintenant = LocalDate.now();
		Period period = Period.between(ddn, maintenant);
		return period.getYears();
	}

	/**
	 * Hash un mot de passe
	 *
	 * @param mdp
	 * @return
	 */
	public static String hashGuava(String mdp) {
		return Hashing.sha512().hashString(mdp.trim(), StandardCharsets.UTF_8).toString();
	}

	/**
	 *
	 * @param mot
	 * @return
	 */
	public static boolean isNullOrWithSpace(String mot) {
		return mot == null || mot.trim().length() == 0;
	}
}
