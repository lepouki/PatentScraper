package scraper.application.parsers;

import scraper.application.CsvParser;
import scraper.core.Document;

import java.util.*;

public class GooglePatentsCsvParser extends CsvParser {

	@Override
	protected Set<Document> parse(String filePath) {
		return new HashSet<>(0);
	}

}
