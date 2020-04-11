package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.PageProcessor;
import scraper.core.writers.BasicFileWriter;
import scraper.core.writers.DummyFileWriter;

public class PageScraper extends PropertyScraper {

	public PageScraper() {
		super(
			new BasicFileWriter(),
			new PageProcessor()
		);
	}

	@Override
	public String getRelativeFileWriterPath() {
		return "a/b/c/d/test";
	}

}
