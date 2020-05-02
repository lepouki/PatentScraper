package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;
import scraper.core.PropertyScraper;
import scraper.core.properties.*;

import java.util.*;

public class ExtraFilesOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Also scrape";

	public ExtraFilesOptionGroup(PageScraper pageScraper) {
		super(TITLE);
		createOptionPropertyScrapers(pageScraper);
	}

	private void createOptionPropertyScrapers(PageScraper pageScraper) {
		List<PropertyScraper> extraInformationScrapers = new ArrayList<>();

		extraInformationScrapers.add(
			new PdfScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new FiguresScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new EventsScraper(pageScraper)
		);

		extraInformationScrapers.add(
			new ClassificationsScraper(pageScraper)
		);

		setOptionPropertyScrapers(extraInformationScrapers);
	}

}
