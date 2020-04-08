package scraper.application.groups;

import scraper.application.Application;
import scraper.application.LayoutConfiguration;
import scraper.application.widgets.ScraperControllerButtons;
import scraper.application.widgets.ScraperProgressBars;
import scraper.application.WidgetGroup;

import javax.swing.*;
import java.awt.*;

public class ScraperControls extends WidgetGroup {

	private static final String TITLE = "Scraper controls";
	private static final String STATUS_LABEL_IDLE_TEXT = "Idle";

	private ScraperControllerButtons scraperControllerButtons;
	private JLabel statusLabel;
	private ScraperProgressBars scraperProgressBars;

	public ScraperControls(Application application) {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createComponents(application);
	}

	private void createComponents(Application application) {
		scraperControllerButtons = new ScraperControllerButtons(application);
		add(scraperControllerButtons);

		createComponentSeparator();
		createStatusLabel();

		createComponentSeparator();
		scraperProgressBars = new ScraperProgressBars(LayoutConfiguration.PADDING);
		add(scraperProgressBars);
	}

	private void createComponentSeparator() {
		Component separator = Box.createRigidArea(
			new Dimension(0, LayoutConfiguration.PADDING)
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
