package scraper.application;

import scraper.application.groups.InputOutputChooser;
import scraper.application.groups.ScraperControls;
import scraper.application.groups.ScraperOptionsPicker;
import scraper.core.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String INVALID_INPUT_OUTPUT_MESSAGE = "Invalid input file and/or output directory";
	private static final String WORK_DONE_MESSAGE = "Done";
	private static final String ABORTING_WORK_MESSAGE = "Processing final document and aborting";

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	private Worker worker;
	private WorkerProgressStringMaker workerProgressStringMaker;

	public Application() {
		super(TITLE);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		createGroups();
		workerProgressStringMaker = new WorkerProgressStringMaker();

		configureFrame();
	}

	private void createGroups() {
		inputOutputChooser = new InputOutputChooser();
		add(inputOutputChooser);

		scraperOptionsPicker = new ScraperOptionsPicker();
		add(scraperOptionsPicker);

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
		boolean isInputOutputValid = isInputOutputValid();

		if (!isInputOutputValid) {
			scraperControls.setStatusText(INVALID_INPUT_OUTPUT_MESSAGE);
			return;
		}

		updateScraperControlsWorkerStarting();
		runWorker();
	}

	private boolean isInputOutputValid() {
		String inputFilePathText = inputOutputChooser.getInputFilePathText();
		String outputDirectoryPathText = inputOutputChooser.getOutputDirectoryPathText();
		return checkPath(inputFilePathText) && checkPath(outputDirectoryPathText);
	}

	private static boolean checkPath(String pathText) {
		Path path = Paths.get(pathText);
		return !pathText.isEmpty() && Files.exists(path);
	}

	private void updateScraperControlsWorkerStarting() {
		scraperControls.toggleButtons();
		scraperControls.setProgressBarsValue(0);
	}

	private void runWorker() {
		List<Document> documents = getDocuments();
		worker = new Worker(this, createScraper(), documents);
		worker.execute();
	}

	private Scraper createScraper() {
		List<PropertyScraper> propertyScrapers = createPropertyScrapers();
		return new Scraper(propertyScrapers);
	}

	private List<PropertyScraper> createPropertyScrapers() {
		List<PropertyRetriever> propertyRetrievers = scraperOptionsPicker.getPropertyRetrievers();
		return new ArrayList<>(0);
	}

	private List<Document> getDocuments() {
		return new ArrayList<>(0);
	}

	public void onAbortButtonPressed() {
		scraperControls.setStatusText(ABORTING_WORK_MESSAGE);
		worker.cancel(true);
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		updateProgressBarsText(event);
		updateProgressBarsValues(event);
	}

	private void updateProgressBarsText(ProgressEvent event) {
		workerProgressStringMaker.processWorkerProgressEvent(event);
		String workerProgressString = workerProgressStringMaker.makeProgressString();
		scraperControls.setStatusText(workerProgressString);
	}

	private void updateProgressBarsValues(ProgressEvent event) {
		Progress progress = event.getProgress();
		int progressValue = (int)progress.getPercentage();

		if (event instanceof Worker.ProcessingDocumentEvent) {
			scraperControls.setDocumentProgressBarValue(progressValue);
		}
		else if (event instanceof Scraper.ProcessingPropertyEvent) {
			scraperControls.setPropertyProgressBarValue(progressValue);
		}
	}

	public void onWorkerDone() {
		scraperControls.resetButtons();
		scraperControls.setStatusText(WORK_DONE_MESSAGE);
		scraperControls.setProgressBarsValue(100);
	}

}
