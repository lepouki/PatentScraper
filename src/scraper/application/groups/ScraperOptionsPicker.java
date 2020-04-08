package scraper.application.groups;

import scraper.application.LayoutConfiguration;
import scraper.application.WidgetGroup;
import scraper.application.widgets.PropertyRetrieverOptionGroup;
import scraper.core.PropertyRetriever;

import java.util.ArrayList;
import java.util.List;

public class ScraperOptionsPicker extends WidgetGroup {

	private static final String TITLE = "Options";
	private static final String DATA_FRAME_ADDITIONS_GROUP_TITLE = "Data frame additions";
	private static final String CITATIONS_GROUP_TITLE = "Citations";
	private static final String PROPERTIES_GROUP_TITLE = "Properties";

	PropertyRetrieverOptionGroup dataFrameAdditionsGroup;
	PropertyRetrieverOptionGroup citationsGroup;
	PropertyRetrieverOptionGroup propertiesGroup;

	public ScraperOptionsPicker() {
		super(TITLE, LayoutConfiguration.PADDING);
		createPropertyRetrieverGroups();
	}

	private void createPropertyRetrieverGroups() {
		createDataFrameAdditionsGroup();
		createCitationsGroup();
		createPropertiesGroup();
	}

	private void createDataFrameAdditionsGroup() {
	}

	private void createCitationsGroup() {
	}

	private void createPropertiesGroup() {
	}

	public List<PropertyRetriever> getPropertyRetrievers() {
		return new ArrayList<>(0);
	}

}
