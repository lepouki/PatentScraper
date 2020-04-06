package scraper.application;

import scraper.application.widgets.OptionsPicker;
import scraper.application.widgets.ScraperControllerButtons;
import scraper.application.widgets.ScraperProgressBars;
import scraper.core.Progress;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;

import javax.swing.*;
import java.util.ArrayList;

public class Application extends JFrame {

	private static final String APPLICATION_NAME = "Scraper";
	private static final String OPTIONS_TITLE = "Options";
	private static final String WORKER_DONE_MESSAGE = "Work done";

	private ScraperControllerButtons scraperControllerButtons;
	private ScraperProgressBars scraperProgressBars;

	private WorkerProgressStringMaker workerProgressStringMaker;

	public Application() {
		super(APPLICATION_NAME);

		createContentPane();

		createOptionsPicker();
		createScraperControllerButtons();
		createScraperProgressBars();

		workerProgressStringMaker = new WorkerProgressStringMaker();

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

	private void createOptionsPicker() {
		OptionsPicker optionsPicker = new OptionsPicker(OPTIONS_TITLE);

		add(optionsPicker);
	}

	private void createScraperControllerButtons() {
		scraperControllerButtons = new ScraperControllerButtons(this);

		add(scraperControllerButtons);
	}

	private void createScraperProgressBars() {
		scraperProgressBars = new ScraperProgressBars();

		add(scraperProgressBars);
	}

	public void onStartButtonPressed() {
	}

	public void onAbortButtonPressed() {
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		workerProgressStringMaker.processWorkerProgressEvent(event);

		if (event instanceof Scraper.ProcessingPropertyEvent) {
			String workerProgressString = workerProgressStringMaker.makeProgressString();
			scraperProgressBars.setProgressionText(workerProgressString);
		}
	}

	public void onWorkerDone() {
		scraperControllerButtons.resetButtons();
		scraperProgressBars.setProgressionText(WORKER_DONE_MESSAGE);
		scraperProgressBars.setProgressBarsComplete();
	}

}
