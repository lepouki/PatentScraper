package scraper.application.widgets;

import scraper.application.*;

import javax.swing.*;
import java.awt.*;

public class CheckboxGroup extends Widget {


	public CheckboxGroup() {
		this(LayoutConfiguration.OPTIONS_PER_ROW);
	}

	public CheckboxGroup(int optionsPerRow) {
		GridLayout layout = new GridLayout(0, optionsPerRow);
		setLayout(layout);
	}

	protected JCheckBox createCheckbox(String text) {
		JCheckBox checkBox = new JCheckBox(text);
		add(checkBox);
		checkBox.setSelected(true);
		return checkBox;
	}

}
