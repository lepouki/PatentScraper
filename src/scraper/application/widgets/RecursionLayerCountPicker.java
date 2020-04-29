package scraper.application.widgets;

import scraper.application.Widget;

import java.awt.*;

public class RecursionLayerCountPicker extends Widget {

	private static final String TITLE = "Layer count";
	private static final int MINIMUM_VALUE = 1;
	private static final int MAXIMUM_VALUE = 10;

	private final IntegerSpinner layerCountSpinner;

	public RecursionLayerCountPicker() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		layerCountSpinner = new IntegerSpinner(TITLE, MINIMUM_VALUE, MAXIMUM_VALUE);
		add(layerCountSpinner, BorderLayout.CENTER);
	}

	public int getLayerCount() {
		return layerCountSpinner.getValue();
	}

}
