package scraper.application.widgets;

import scraper.application.*;

import javax.swing.*;

public class ScraperProgressBars extends Widget {

	private JProgressBar layerProgressBar, documentProgressBar;

	public ScraperProgressBars() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createProgressBars();
	}

	private void createProgressBars() {
		layerProgressBar = new JProgressBar();
		add(layerProgressBar);

		createComponentSeparator();

		documentProgressBar = new JProgressBar();
		add(documentProgressBar);
	}

	public void setLayerProgressBarValue(int value) {
		layerProgressBar.setValue(value);
	}

	public void setDocumentProgressBarValue(int value) {
		documentProgressBar.setValue(value);
	}

	public void setProgressBarsValue(int value) {
		setLayerProgressBarValue(value);
		setDocumentProgressBarValue(value);
	}

}
