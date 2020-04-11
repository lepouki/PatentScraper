package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;

import java.awt.*;
import javax.swing.*;

public class ScraperControls extends WidgetGroup {

	private static final String TITLE = "Scraper controls";
	private static final String STATUS_LABEL_IDLE_TEXT = "Idle";

	private ScraperButtons scraperButtons;
	private JLabel statusLabel;
	private JProgressBar scraperProgressBar;

	public ScraperControls(Application application) {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createComponents(application);
	}

	private void createComponents(Application application) {
		scraperButtons = new ScraperButtons(application);
		add(scraperButtons);

		createComponentSeparator();
		createStatusLabel();

		createComponentSeparator();
		scraperProgressBar = new JProgressBar();
		add(scraperProgressBar);
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
		scraperButtons.toggleButtons();
	}

	public void resetButtons() {
		scraperButtons.resetButtons();
	}

	public void setProgressBarValue(int value) {
		scraperProgressBar.setValue(value);
	}

	public void setStatus(String status) {
		statusLabel.setText(status);
	}

}
