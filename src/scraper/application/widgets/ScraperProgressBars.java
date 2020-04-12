package scraper.application.widgets;

import scraper.application.LayoutConfiguration;

import javax.swing.*;
import java.awt.*;

public class ScraperProgressBars extends JPanel {

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

	private void createComponentSeparator() {
		Component separator = Box.createRigidArea(
			new Dimension(0, LayoutConfiguration.PADDING)
		);

		add(separator);
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
