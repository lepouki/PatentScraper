package scraper.application.widgets;

import scraper.application.Application;

import javax.swing.*;

public class ScraperControls extends Group {

	private static final String TITLE = "Scraper controls";

	private ScraperControllerButtons scraperControllerButtons;
	private ScraperProgressBars scraperProgressBars;

	public ScraperControls(Application application) {
		super(TITLE);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		scraperControllerButtons = new ScraperControllerButtons(application);
		add(scraperControllerButtons);

		scraperProgressBars = new ScraperProgressBars();
		add(scraperProgressBars);
	}

	public void resetButtons() {
		scraperControllerButtons.resetButtons();
	}

	public void setProgressBarsComplete() {
		scraperProgressBars.setDocumentProgressBarValue(100);
		scraperProgressBars.setPropertyProgressBarValue(100);
	}

	public void setProgressBarsProgressionText(String text) {
		scraperProgressBars.setProgressionText(text);
	}

}
