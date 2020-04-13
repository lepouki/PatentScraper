package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.processors.*;

public class InventorOrAuthorScraper extends PropertyScraper {

	public InventorOrAuthorScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new InventorOrAuthorProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
