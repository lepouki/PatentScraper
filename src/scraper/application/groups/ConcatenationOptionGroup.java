package scraper.application.groups;

import scraper.application.widgets.*;
import scraper.core.PropertyScraper;
import scraper.core.scrapers.*;

import java.util.List;

public class ConcatenationOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Concatenate text";

	private final ConcatenationScraper concatenationScraper;
	private final ConcatenationCheckboxGroup concatenationCheckboxGroup;

	public ConcatenationOptionGroup(PageScraper pageScraper) {
		super(TITLE);

		concatenationScraper = new ConcatenationScraper(pageScraper);
		pushPreparationPropertyScraper(concatenationScraper);

		concatenationCheckboxGroup = new ConcatenationCheckboxGroup();
		setContent(concatenationCheckboxGroup);
	}

	@Override
	public List<PropertyScraper> getPropertyScrapers() {
		concatenationScraper.setConcatenationOptions(
			concatenationCheckboxGroup.getConcatenationOptions()
		);

		return super.getPropertyScrapers();
	}

}
