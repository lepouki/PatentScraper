package scraper.application.widgets;

import java.awt.*;
import javax.swing.*;

public class ScraperProgressBars extends JPanel {

	private JProgressBar documentProgressBar, propertyProgressBar;

	public ScraperProgressBars(int separatorSize) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createProgressBars(separatorSize);
	}

	private void createProgressBars(int separatorSize) {
		documentProgressBar = new JProgressBar();
		add(documentProgressBar);

		createSeparator(separatorSize);

		propertyProgressBar = new JProgressBar();
		add(propertyProgressBar);
	}

	private void createSeparator(int separatorSize) {
		Component separator = Box.createRigidArea(
			new Dimension(0, separatorSize)
		);

		add(separator);
	}

	public void setDocumentProgressBarValue(int value) {
		documentProgressBar.setValue(value);
	}

	public void setPropertyProgressBarValue(int value) {
		propertyProgressBar.setValue(value);
	}

}
