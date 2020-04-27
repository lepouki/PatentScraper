package scraper.application.widgets;

import scraper.application.Widget;

import javax.swing.*;
import java.awt.*;

public class LanguageCheckboxGroup extends Widget {

	private static final String ENGLISH_CHECKBOX_TEXT = "English";
	private static final String NATIVE_LANGUAGE_CHECKBOX_TEXT = "Native";

	private final JCheckBox nativeLanguageCheckbox;

	public LanguageCheckboxGroup() {
		setAlignmentX(Component.LEFT_ALIGNMENT);

		ButtonGroup checkboxGroup = new ButtonGroup();
		nativeLanguageCheckbox = new JCheckBox(NATIVE_LANGUAGE_CHECKBOX_TEXT);
		add(nativeLanguageCheckbox);
		checkboxGroup.add(nativeLanguageCheckbox);

		JCheckBox englishCheckbox = new JCheckBox(ENGLISH_CHECKBOX_TEXT);
		englishCheckbox.setSelected(true);
		add(englishCheckbox);
		checkboxGroup.add(englishCheckbox);
	}

	public boolean useNativeLanguage() {
		return nativeLanguageCheckbox.isSelected();
	}

}
