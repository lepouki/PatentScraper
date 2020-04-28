package scraper.application.widgets;

import scraper.application.Widget;

import javax.swing.*;
import java.awt.*;

public class LanguageCheckboxGroup extends Widget {

	private static final String ENGLISH_CHECKBOX_TEXT = "English";
	private static final String NATIVE_LANGUAGE_CHECKBOX_TEXT = "Native";

	private JCheckBox nativeLanguageCheckbox;

	public LanguageCheckboxGroup() {
		GridLayout layout = new GridLayout(0, 2);
		setLayout(layout);
		createLanguageCheckboxes();
	}

	private void createLanguageCheckboxes() {
		ButtonGroup checkboxGroup = new ButtonGroup();
		nativeLanguageCheckbox = createCheckbox(checkboxGroup, NATIVE_LANGUAGE_CHECKBOX_TEXT, false);
		createCheckbox(checkboxGroup, ENGLISH_CHECKBOX_TEXT, true);
	}

	private JCheckBox createCheckbox(ButtonGroup checkboxGroup, String text, boolean selected) {
		JCheckBox checkBox = new JCheckBox(text);

		add(checkBox);
		checkboxGroup.add(checkBox);

		checkBox.setSelected(selected);
		return checkBox;
	}

	public boolean useNativeLanguage() {
		return nativeLanguageCheckbox.isSelected();
	}

}
