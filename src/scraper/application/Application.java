package scraper.application;

import scraper.application.widgets.*;
import scraper.core.ProgressEvent;
import scraper.core.Scraper;

import javax.swing.*;

public class Application extends JFrame {

	private InputOutputChooser inputOutputChooser;
	private OptionsPicker optionsPicker;
	private ScraperControls scraperControls;

	private WorkerProgressStringMaker workerProgressStringMaker;

	public Application() {
		super("Scraper");

		createMainPane();
		createGroups();
		createUtilities();

		configureFrame();
	}

	private void createMainPane() {
		MainPane mainPane = new MainPane();

		setContentPane(mainPane);
	}

	private void createGroups() {
		inputOutputChooser = new InputOutputChooser();
		add(inputOutputChooser);

		optionsPicker = new OptionsPicker();
		add(optionsPicker);

		scraperControls = new ScraperControls(this);
		add(scraperControls);
	}

	private void createUtilities() {
		workerProgressStringMaker = new WorkerProgressStringMaker();
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
		scraperControls.setProgressBarsProgressionText("Work done");
		scraperControls.setProgressBarsComplete();
	}

}
