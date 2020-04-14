package scraper.application.groups;

import scraper.application.widgets.PropertyScraperOptionGroup;

import java.util.ArrayList;

public class ExtraInformationOptionGroup extends PropertyScraperOptionGroup {

	private static final String TITLE = "Extra information";

	public ExtraInformationOptionGroup() {
		super(TITLE);

		setPropertyScrapers(
			new ArrayList<>(0)
		);
	}

}
