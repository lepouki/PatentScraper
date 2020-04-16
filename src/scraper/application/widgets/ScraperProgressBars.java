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
		layerProgressBar = createProgressBar();
		createComponentSeparator();
		documentProgressBar = createProgressBar();
	}

	private JProgressBar createProgressBar() {
		JProgressBar progressBar = new JProgressBar();
		add(progressBar);

		progressBar.setStringPainted(true);
		progressBar.setString("");

		return progressBar;
	}

	public void setLayerProgressBarText(String text) {
		layerProgressBar.setString(text);
	}

	public void setDocumentProgressBarText(String text) {
		documentProgressBar.setString(text);
	}

	public void setLayerProgressBarValue(int value) {
		layerProgressBar.setValue(value);
	}

	public void setDocumentProgressBarValue(int value) {
		documentProgressBar.setValue(value);
	}

	public void resetProgressBars() {
		final String emptyString = "";

		setLayerProgressBarValue(0);
		setLayerProgressBarText(emptyString);

		setDocumentProgressBarValue(0);
		setDocumentProgressBarText(emptyString);
	}

}
