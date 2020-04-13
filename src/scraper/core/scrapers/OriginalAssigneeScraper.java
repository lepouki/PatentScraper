package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.processors.*;

public class OriginalAssigneeScraper extends PropertyScraper {

	public OriginalAssigneeScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new OriginalAssigneeProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
