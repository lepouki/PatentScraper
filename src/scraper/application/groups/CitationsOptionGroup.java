package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;

import java.util.ArrayList;

public class CitationsOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Citations";

	public CitationsOptionGroup() {
		super(TITLE);

		setPropertyScrapers(
			new ArrayList<>(0)
		);
	}

}
