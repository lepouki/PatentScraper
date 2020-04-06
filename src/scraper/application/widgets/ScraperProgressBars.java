package scraper.application.widgets;

import javax.swing.*;
import java.awt.*;

public class ScraperProgressBars extends JPanel {

	private static final int PROGRESS_BAR_SEPARATOR_SIZE = 5;
	private static final String IDLE_PROGRESSION_LABEL_TEXT = "Idle";

	private JLabel progressionLabel;
	private JProgressBar documentProgressBar, propertyProgressBar;

	public ScraperProgressBars() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createProgressionLabel();

		documentProgressBar = new JProgressBar();
		add(documentProgressBar);

		createProgressBarEmptySeparator();

		propertyProgressBar = new JProgressBar();
		add(propertyProgressBar);
	}

	private void createProgressionLabel() {
		progressionLabel = new JLabel(IDLE_PROGRESSION_LABEL_TEXT);
		progressionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(progressionLabel);
	}

	private void createProgressBarEmptySeparator() {
		Dimension emptySeparatorDimension = new Dimension(0, PROGRESS_BAR_SEPARATOR_SIZE);
		Component emptySeparator = Box.createRigidArea(emptySeparatorDimension);
		add(emptySeparator);
	}

	public void setProgressionText(String text) {
		progressionLabel.setText(text);
	}

	public void setDocumentProgressBarValue(int value) {
		documentProgressBar.setValue(value);
	}

	public void setPropertyProgressBarValue(int value) {
		propertyProgressBar.setValue(value);
	}

}
