package scraper.application;

import scraper.core.Document;

import java.io.IOException;
import java.util.Set;

public abstract class CsvParser {

	public static class FormatException extends IOException {

		public FormatException(String message) {
			super(message);
		}

	}

	public abstract Set<Document> parseFile(String filePath) throws FormatException;

}
