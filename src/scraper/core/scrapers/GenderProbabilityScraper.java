package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.*;

public class GenderProbabilityScraper extends PropertyScraper {

	public GenderProbabilityScraper(GenderProcessor genderProcessor) {
		super(
			new GenderProbabilityProcessor(genderProcessor)
		);
	}

}
