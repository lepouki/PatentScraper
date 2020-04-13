package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.PageProcessor;
import scraper.core.writers.DummyFileDataWriter;

public class PageScraper extends PropertyScraper {

	public PageScraper() {
		super(
			new DummyFileDataWriter(),
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
