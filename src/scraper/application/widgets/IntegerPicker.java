package scraper.application.widgets;

import javax.swing.*;

public class IntegerPicker extends JPanel {

	private static final int TEXT_FIELD_COLUMN_COUNT = 2;

	private JSpinner integerSpinner;

	public IntegerPicker(String title, int minimumValue, int maximumValue) {
		createTitleLabel(title, maximumValue);
		createIntegerSpinner(minimumValue, maximumValue);
		setSpinnerColumnCount();
	}

	private void createTitleLabel(String title, int maximumValue) {
		JLabel titleLabel = new JLabel(title + " (" + maximumValue + " maximum)");
		add(titleLabel);
	}

	private void createIntegerSpinner(int minimumValue, int maximumValue) {
		integerSpinner = new JSpinner();
		add(integerSpinner);
		final int stepSize = 1;

		integerSpinner.setModel(
			new SpinnerNumberModel(minimumValue, minimumValue, maximumValue, stepSize)
		);
	}

	private void setSpinnerColumnCount() {
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)integerSpinner.getEditor();
		editor.getTextField().setColumns(TEXT_FIELD_COLUMN_COUNT);
	}

	public int getValue() {
		return (Integer)integerSpinner.getValue();
	}

}
