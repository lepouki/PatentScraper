package scraper.application.widgets;

import scraper.application.Widget;
import scraper.core.ConcatenationOptions;

import javax.swing.*;
import java.awt.*;

public class ConcatenationCheckboxGroup extends Widget {

	private static final String ABSTRACT_CHECKBOX_TEXT = "Abstract";
	private static final String DESCRIPTION_CHECKBOX_TEXT = "Description";
	private static final String CLAIMS_CHECKBOX_TEXT = "Claims";

	private JCheckBox abstractCheckbox;
	private JCheckBox descriptionCheckbox;
	private JCheckBox claimsCheckbox;

	public ConcatenationCheckboxGroup() {
		GridLayout layout = new GridLayout(0, 1);
		setLayout(layout);
		createConcatenationCheckboxes();
	}

	private void createConcatenationCheckboxes() {
		abstractCheckbox = createCheckbox(ABSTRACT_CHECKBOX_TEXT);
		descriptionCheckbox = createCheckbox(DESCRIPTION_CHECKBOX_TEXT);
		claimsCheckbox = createCheckbox(CLAIMS_CHECKBOX_TEXT);
	}

	private JCheckBox createCheckbox(String text) {
		JCheckBox checkBox = new JCheckBox(text);
		add(checkBox);
		checkBox.setSelected(true);
		return checkBox;
	}

	public ConcatenationOptions getConcatenationOptions() {
		return new ConcatenationOptions(
			abstractCheckbox.isSelected(), descriptionCheckbox.isSelected(), claimsCheckbox.isSelected()
		);
	}

}
