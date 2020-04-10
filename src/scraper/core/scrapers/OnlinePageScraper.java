package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.OnlinePageProcessor;
import scraper.core.writers.DummyDataWriter;

public class OnlinePageScraper extends PropertyScraper {

	public OnlinePageScraper() {
		super(
			new DummyDataWriter(),
			new OnlinePageProcessor()
		);
	}

}
