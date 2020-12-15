package scraper.application;

import scraper.core.CsvCharacters;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CustomCsvChecker {

	private static final String GOOGLE_PATENTS_CSV_EXPECTED_FIRST_COLUMN = "search URL:";

	public static boolean isCustomCsv(String filePath) {
		String firstLine = retrieveFirstLine(filePath);
		return !getFirstColumnInLine(firstLine).equals(GOOGLE_PATENTS_CSV_EXPECTED_FIRST_COLUMN);
	}

	private static String retrieveFirstLine(String filePath) {
		Scanner scanner = createFileScanner(filePath);

		String firstLine = tryRetrieveLine(scanner);
		scanner.close();

		return firstLine;
	}

	private static Scanner createFileScanner(String filePath) {
		File file = new File(filePath);
		return tryCreateScanner(file);
	}

	private static Scanner tryCreateScanner(File file) {
		try {
			return new Scanner(file, StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			return null; // This should never happen, we know the input file exists at this point
		}
	}

	private static String tryRetrieveLine(Scanner scanner) {
		try {
			return scanner.nextLine();
		}
		catch (NoSuchElementException exception) {
			return "";
		}
	}

	private static String getFirstColumnInLine(String line) {
		String separator = Character.toString(CsvCharacters.SEPARATOR);
		return line.split(separator)[0];
	}

}
