package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.processors.*;

public class TitleScraper extends PropertyScraper {

	public TitleScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new TitleProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
