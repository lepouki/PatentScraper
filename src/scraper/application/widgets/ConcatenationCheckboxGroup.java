package scraper.application.widgets;

import scraper.core.ConcatenationOptions;

import javax.swing.*;

public class ConcatenationCheckboxGroup extends CheckboxGroup {

	private static final String TITLE_CHECKBOX_TEXT = "Title";
	private static final String ABSTRACT_CHECKBOX_TEXT = "Abstract";
	private static final String DESCRIPTION_CHECKBOX_TEXT = "Description";
	private static final String CLAIMS_CHECKBOX_TEXT = "Claims";

	private final JCheckBox titleCheckbox;
	private final JCheckBox abstractCheckbox;
	private final JCheckBox descriptionCheckbox;
	private final JCheckBox claimsCheckbox;

	public ConcatenationCheckboxGroup() {
		titleCheckbox = createCheckbox(TITLE_CHECKBOX_TEXT);
		abstractCheckbox = createCheckbox(ABSTRACT_CHECKBOX_TEXT);
		descriptionCheckbox = createCheckbox(DESCRIPTION_CHECKBOX_TEXT);
		claimsCheckbox = createCheckbox(CLAIMS_CHECKBOX_TEXT);
	}

	public ConcatenationOptions getConcatenationOptions() {
		return new ConcatenationOptions(
			titleCheckbox.isSelected(),
			abstractCheckbox.isSelected(),
			descriptionCheckbox.isSelected(),
			claimsCheckbox.isSelected()
		);
	}

}
