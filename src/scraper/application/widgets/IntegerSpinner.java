package scraper.application.widgets;

import scraper.application.Widget;

import javax.swing.*;

public class IntegerSpinner extends Widget {

	private static final int TEXT_FIELD_COLUMN_COUNT = 2;

	private JSpinner spinner;

	public IntegerSpinner(String title, int minimumValue, int maximumValue) {
		createTitleLabel(title);
		createIntegerSpinner(minimumValue, maximumValue);
		setIntegerSpinnerColumnCount();
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		add(titleLabel);
	}

	private void createIntegerSpinner(int minimumValue, int maximumValue) {
		spinner = new JSpinner();
		add(spinner);
		final int stepSize = 1;

		spinner.setModel(
			new SpinnerNumberModel(minimumValue, minimumValue, maximumValue, stepSize)
		);
	}

	private void setIntegerSpinnerColumnCount() {
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)spinner.getEditor();
		editor.getTextField().setColumns(TEXT_FIELD_COLUMN_COUNT);
	}

	public int getValue() {
		return (Integer)spinner.getValue();
	}

}
