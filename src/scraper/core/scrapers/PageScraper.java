package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.PageProcessor;

public class PageScraper extends PropertyScraper {

	public PageScraper() {
		super(
			new PageProcessor()
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
