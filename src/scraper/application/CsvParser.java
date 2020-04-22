package scraper.application;

import scraper.core.Document;

import java.io.IOException;
import java.util.List;

public interface CsvParser {

	class FormatException extends IOException {

		public FormatException(String message) {
			super(message);
		}

	}

	List<Document> parseDocuments(String filePath) throws FormatException;

}
