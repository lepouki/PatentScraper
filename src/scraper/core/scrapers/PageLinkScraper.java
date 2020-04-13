package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.processors.*;

public class PageLinkScraper extends PropertyScraper {

	public PageLinkScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new PageLinkProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
