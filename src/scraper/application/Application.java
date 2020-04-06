package scraper.application;

import scraper.application.widgets.*;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;

import javax.swing.*;

public class Application extends JFrame {

	private static final String NAME = "Scraper";
	private static final String WORK_DONE_MESSAGE = "Work done";

	private InputOutputChooser inputOutputChooser;
	private OptionsPicker optionsPicker;
	private ScraperControls scraperControls;

	private WorkerProgressStringMaker workerProgressStringMaker;

	public Application() {
		super(NAME);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		createGroups();
		workerProgressStringMaker = new WorkerProgressStringMaker();

		configureFrame();
	}

	private void createGroups() {
		inputOutputChooser = new InputOutputChooser();
		add(inputOutputChooser);

		optionsPicker = new OptionsPicker();
		add(optionsPicker);

		scraperControls = new ScraperControls(this);
		add(scraperControls);
	}

	private void configureFrame() {
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void onStartButtonPressed() {
	}

	public void onAbortButtonPressed() {
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		workerProgressStringMaker.processWorkerProgressEvent(event);

		if (event instanceof Scraper.ProcessingPropertyEvent) {
			String workerProgressString = workerProgressStringMaker.makeProgressString();
			scraperControls.setProgressBarsProgressionText(workerProgressString);
		}
	}

	public void onWorkerDone() {
		scraperControls.resetButtons();
		scraperControls.setProgressBarsProgressionText(WORK_DONE_MESSAGE);
		scraperControls.setProgressBarsComplete();
	}

}
