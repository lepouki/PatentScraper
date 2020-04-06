package scraper.application.widgets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScraperProgressBars extends JPanel {

	private static final int PADDING_SIZE = 5;
	private static final String IDLE_PROGRESSION_LABEL_TEXT = "Idle";

	private JLabel progressionLabel;
	private JProgressBar documentProgressBar, propertyProgressBar;

	public ScraperProgressBars() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createProgressionLabel();
		createProgressBars();

		createPaddingBorder();
	}

	private void createPaddingBorder() {
		Border paddingBorder = new EmptyBorder(0, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);

		setBorder(paddingBorder);
	}

	private void createProgressionLabel() {
		progressionLabel = new JLabel(IDLE_PROGRESSION_LABEL_TEXT);
		progressionLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		add(progressionLabel);
	}

	private void createProgressBars() {
		createDocumentProgressBar();
		createProgressBarSeparator();
		createPropertyProgressBar();
	}

	private void createDocumentProgressBar() {
		documentProgressBar = new JProgressBar();

		add(documentProgressBar);
	}

	private void createProgressBarSeparator() {
		Dimension separatorDimension = new Dimension(0, PADDING_SIZE);
		Component progressBarSeparator = Box.createRigidArea(separatorDimension);

		add(progressBarSeparator);
	}

	private void createPropertyProgressBar() {
		propertyProgressBar = new JProgressBar();

		add(propertyProgressBar);
	}

	public void setProgressionText(String text) {
		progressionLabel.setText(text);
	}

	public void setProgressBarsComplete() {
		setDocumentProgressBarValue(100);
		setPropertyProgressBarValue(100);
	}

	public void setDocumentProgressBarValue(int value) {
		documentProgressBar.setValue(value);
	}

	public void setPropertyProgressBarValue(int value) {
		propertyProgressBar.setValue(value);
	}

}
