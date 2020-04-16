package scraper.application;

import scraper.core.Document;

import java.io.IOException;
import java.util.Set;

public interface CsvParser {

	class FormatException extends IOException {

		public FormatException(String message) {
			super(message);
		}

	}

	Set<Document> parseFile(String filePath) throws FormatException;

}
