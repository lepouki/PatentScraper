package scraper.application.widgets;

import scraper.application.Widget;

import javax.swing.*;

public class CitationLayerCountPicker extends Widget {

	private static final String TITLE = "Citation layer count";
	private static final int TEXT_FIELD_COLUMN_COUNT = 2;
	private static final int MINIMUM_VALUE = 1;
	private static final int MAXIMUM_VALUE = 50;

	private JSpinner integerSpinner;

	public CitationLayerCountPicker() {
		JLabel titleLabel = new JLabel(TITLE);
		add(titleLabel);

		createIntegerSpinner();
		setSpinnerColumnCount();
	}

	private void createIntegerSpinner() {
		integerSpinner = new JSpinner();
		add(integerSpinner);
		final int stepSize = 1;

		integerSpinner.setModel(
			new SpinnerNumberModel(MINIMUM_VALUE, MINIMUM_VALUE, MAXIMUM_VALUE, stepSize)
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
