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
	private ScraperProgressBars scraperProgressBars;

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
		scraperProgressBars = new ScraperProgressBars();
		add(scraperProgressBars);
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

	public void setStatus(String status) {
		statusLabel.setText(status);
	}

	public void setLayerProgressBarText(String text) {
		scraperProgressBars.setLayerProgressBarText(text);
	}

	public void setDocumentProgressBarText(String text) {
		scraperProgressBars.setDocumentProgressBarText(text);
	}

	public void setLayerProgressBarValue(int value) {
		scraperProgressBars.setLayerProgressBarValue(value);
	}

	public void setDocumentProgressBarValue(int value) {
		scraperProgressBars.setDocumentProgressBarValue(value);
	}

	public void resetProgressBars() {
		scraperProgressBars.resetProgressBars();
	}

}
