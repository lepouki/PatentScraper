package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.processors.*;

public class CurrentAssigneeScraper extends PropertyScraper {

	public CurrentAssigneeScraper(FileDataWriter fileDataWriter, PageProcessor pageProcessor) {
		super(
			fileDataWriter,
			new CurrentAssigneeProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) {
	}

	@Override
	public void cleanup() {
	}

}
