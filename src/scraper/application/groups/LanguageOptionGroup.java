package scraper.application.groups;

import scraper.application.widgets.*;

public class LanguageOptionGroup extends OptionGroup {

	private static final String TITLE = "Language";

	private final LanguageCheckboxGroup languageCheckboxGroup;

	public LanguageOptionGroup() {
		super(TITLE);
		languageCheckboxGroup = new LanguageCheckboxGroup();
		setContent(languageCheckboxGroup);
	}

	public boolean useNativeLanguage() {
		return languageCheckboxGroup.useNativeLanguage();
	}

}
