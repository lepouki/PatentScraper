package scraper.application;

import scraper.application.widgets.FileChooser;
import scraper.application.widgets.InputOutputChooser;
import scraper.application.widgets.RootPanel;
import scraper.application.widgets.ScrapingControllerButtons;
import scraper.core.ProgressEvent;

import javax.swing.*;

public class Application extends JFrame {

	private static final String APPLICATION_NAME = "Scraper";

	private RootPanel rootPanel;

	public Application() {
		super(APPLICATION_NAME);

		rootPanel = new RootPanel();
		add(rootPanel);

		createInputOutputChooser();
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

	private void createInputOutputChooser() {
		InputOutputChooser inputOutputChooser = new InputOutputChooser(this);

		rootPanel.add(inputOutputChooser);
	}

	private void createScrapingControllerButtons() {
		ScrapingControllerButtons scrapingControllerButtons = new ScrapingControllerButtons(this);

		rootPanel.add(scrapingControllerButtons);
	}

}
