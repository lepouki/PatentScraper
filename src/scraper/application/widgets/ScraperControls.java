package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;
import java.awt.*;

public class ScraperControls extends Group {

	private static final String STATUS_LABEL_IDLE_TEXT = "Idle";
	private static final String TITLE = "Scraper controls";

	private ScraperControllerButtons scraperControllerButtons;
	private JLabel statusLabel;
	private ScraperProgressBars scraperProgressBars;

	public ScraperControls(Application application, int padding) {
		super(TITLE, padding);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createComponents(application, padding);
	}

	private void createComponents(Application application, int componentSeparatorSize) {
		scraperControllerButtons = new ScraperControllerButtons(application);
		add(scraperControllerButtons);

		createComponentSeparator(componentSeparatorSize);
		createStatusLabel();

		createComponentSeparator(componentSeparatorSize);
		scraperProgressBars = new ScraperProgressBars(componentSeparatorSize);
		add(scraperProgressBars);
	}

	private void createComponentSeparator(int componentSeparatorSize) {
		Component separator = Box.createRigidArea(
			new Dimension(0, componentSeparatorSize)
		);

		add(separator);
	}

	private void createStatusLabel() {
		statusLabel = new JLabel(STATUS_LABEL_IDLE_TEXT);
		add(statusLabel);
		statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public void toggleButtons() {
		scraperControllerButtons.toggleButtons();
	}

	public void resetButtons() {
		scraperControllerButtons.resetButtons();
	}

	public void setProgressBarsValue(int value) {
		setDocumentProgressBarValue(value);
		setPropertyProgressBarValue(value);
	}

	public void setDocumentProgressBarValue(int value) {
		scraperProgressBars.setDocumentProgressBarValue(value);
	}

	public void setPropertyProgressBarValue(int value) {
		scraperProgressBars.setPropertyProgressBarValue(value);
	}

	public void setStatusText(String statusText) {
		statusLabel.setText(statusText);
	}

}
