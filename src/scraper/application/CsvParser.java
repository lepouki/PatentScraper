package scraper.application;

import scraper.core.Document;

import java.io.IOException;
import java.util.Set;

public abstract class CsvParser {

	public static class FormatException extends IOException {

		public FormatException(String message, String problematicLine) {
			super(message + problematicLine);
		}

	}

	public Set<Document> parseFile(String filePath) throws FormatException {
		return parse(filePath);
	}

	protected abstract Set<Document> parse(String filePath) throws FormatException;

}
