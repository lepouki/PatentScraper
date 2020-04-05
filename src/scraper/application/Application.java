package scraper.application;

import scraper.application.widgets.ScraperControllerButtons;
import scraper.application.widgets.ScraperProgressBars;
import scraper.core.ProgressEvent;

import javax.swing.*;

public class Application extends JFrame {

	private static final String APPLICATION_NAME = "Scraper";

	int testCounter = 0;
	ScraperProgressBars scraperProgressBars;

	public Application() {
		super(APPLICATION_NAME);

		createContentPane();
		createScraperControllerButtons();
		createScraperProgressBars();

		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	private void createContentPane() {
		JPanel contentPane = new JPanel();

		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.PAGE_AXIS);
		contentPane.setLayout(layout);

		setContentPane(contentPane);
	}

	private void createScraperControllerButtons() {
		ScraperControllerButtons scraperControllerButtons = new ScraperControllerButtons(this);

		add(scraperControllerButtons);
	}

	private void createScraperProgressBars() {
		scraperProgressBars = new ScraperProgressBars();

		add(scraperProgressBars);
	}

	public void onStartButtonPressed() {
		testCounter = (testCounter + 1) % 100;
		scraperProgressBars.setDocumentProgressBarValue(testCounter);
	}

	public void onAbortButtonPressed() {
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		System.out.println("Progress");
	}

}
