package scraper.application.groups;

import scraper.application.LayoutConfiguration;
import scraper.application.WidgetGroup;
import scraper.core.PropertyRetriever;

import java.util.ArrayList;
import java.util.List;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);
		createPropertyRetrieverGroups();
	}

	private void createPropertyRetrieverGroups() {
	}

	public List<PropertyRetriever> getPropertyRetrievers() {
		return new ArrayList<>(0);
	}

}
