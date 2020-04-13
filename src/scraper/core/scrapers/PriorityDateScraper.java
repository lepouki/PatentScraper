package scraper.core.scrapers;

import scraper.core.FileDataWriter;
import scraper.core.PropertyScraper;
import scraper.core.processors.PageProcessor;
import scraper.core.processors.PriorityDateProcessor;

public class PriorityDateScraper extends PropertyScraper {

	public PriorityDateScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new PriorityDateProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
