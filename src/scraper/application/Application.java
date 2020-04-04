package scraper.application;

import scraper.application.widgets.ScrapingControllerButtons;
import scraper.core.ProgressEvent;

import javax.swing.JFrame;

public class Application extends JFrame {

	private static final String APPLICATION_NAME = "Scraper";

	public Application() {
		super(APPLICATION_NAME);

		createScrapingControllerButtons();

		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void onStartButtonPressed() {
		System.out.println("Start");
	}

	public void onAbortButtonPressed() {
		System.out.println("Abort");
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		System.out.println("Progress");
	}

	private void createScrapingControllerButtons() {
		ScrapingControllerButtons scrapingControllerButtons = new ScrapingControllerButtons(this);
		add(scrapingControllerButtons);
	}

}
