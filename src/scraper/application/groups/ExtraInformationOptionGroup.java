package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.PropertyScraper;
import scraper.core.scrapers.*;

import java.util.*;

public class ExtraInformationOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Extra information";

	public ExtraInformationOptionGroup(PageScraper pageScraper) {
		super(TITLE);
		createOptionPropertyScrapers(pageScraper);
	}

	private void createOptionPropertyScrapers(PageScraper pageScraper) {
		List<PropertyScraper> extraInformationScrapers = new ArrayList<>();

		extraInformationScrapers.add(
			new PdfScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new AbstractDescriptionScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new DescriptionScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new ClaimsScraper(pageScraper)
		);

		setOptionPropertyScrapers(extraInformationScrapers);
	}

}
