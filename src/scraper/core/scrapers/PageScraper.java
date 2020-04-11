package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.PageProcessor;
import scraper.core.writers.DummyDataWriter;

public class PageScraper extends PropertyScraper {

	public PageScraper() {
		super(
			new DummyDataWriter(),
			new PageProcessor()
		);
	}

}
