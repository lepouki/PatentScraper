package scraper.application.parsers;

import scraper.application.CsvParser;
import scraper.core.Document;

import java.util.*;

public class GooglePatentsCsvParser extends CsvParser {

	@Override
	public Set<Document> parseFile(String filePath) {
		return new HashSet<>(0);
	}

}
