package scraper.application.widgets;

import javax.swing.*;

public class LanguageCheckboxGroup extends CheckboxGroup {

	private static final String ENGLISH_CHECKBOX_TEXT = "English";
	private static final String NATIVE_LANGUAGE_CHECKBOX_TEXT = "Native";

	private final JCheckBox englishLanguageCheckbox;

	public LanguageCheckboxGroup() {
		ButtonGroup checkboxGroup = new ButtonGroup();
		englishLanguageCheckbox = createCheckbox(checkboxGroup, ENGLISH_CHECKBOX_TEXT, true);
		createCheckbox(checkboxGroup, NATIVE_LANGUAGE_CHECKBOX_TEXT, false);
	}

	private JCheckBox createCheckbox(ButtonGroup checkboxGroup, String text, boolean selected) {
		JCheckBox checkBox = new JCheckBox(text);

		add(checkBox);
		checkboxGroup.add(checkBox);

		checkBox.setSelected(selected);
		return checkBox;
	}

	public boolean useNativeLanguage() {
		return !englishLanguageCheckbox.isSelected();
	}

}
