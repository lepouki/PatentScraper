package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String WORK_DONE_MESSAGE = "Done";

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	private Worker worker;
	private final ExceptionHandler exceptionHandler;

	public Application() {
		super(TITLE);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		createGroups();
		exceptionHandler = new ExceptionHandler(scraperControls);

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
		setVisible(true);

		addWindowListener(
			new WindowCloseEventListener(this)
		);
	}

	public void onStartButtonPressed() {
		try {
			initializeWorker();
			updateScraperControlsWorkerStarting();
			worker.execute();
		}
		catch (IOException exception) {
			exceptionHandler.handle(exception);
		}
	}

	private void updateScraperControlsWorkerStarting() {
		scraperControls.toggleButtons();
		scraperControls.setProgressBarValue(0);
	}

	private void initializeWorker() throws IOException {
		Set<Document> documents = getDocuments();
		checkOutputDirectory();
		worker = new Worker(this, createScraper(), documents);
	}

	private Set<Document> getDocuments() throws IOException {
		String inputFilePath = inputOutputChooser.getInputFilePathText();
		return inputOutputChooser.getCsvParser().parseFile(inputFilePath);
	}

	private void checkOutputDirectory() throws IOException {
		String outputDirectoryPath = inputOutputChooser.getOutputDirectoryPathText();
		PathChecker.checkExists(outputDirectoryPath);
	}

	private Scraper createScraper() {
		List<PropertyScraper> propertyScrapers = scraperOptionsPicker.getPropertyScrapers();
		return new Scraper(propertyScrapers);
	}

	public void onAbortButtonPressed() {
		abort();
	}

	public void abort() {
		boolean canCancel = (worker != null) && !worker.isCancelled() && !worker.isDone();

		if (canCancel) {
			worker.cancel(false);
		}
	}

	public void onWorkerProgressMade(Worker.ProgressEvent event) {
		updateProgressBarsText(event);
		updateProgressBarsValues(event);
	}

	private void updateProgressBarsText(Worker.ProgressEvent event) {
		String workerProgressString = event.getDocumentIdentifier();
		scraperControls.setStatusText(workerProgressString);
	}

	private void updateProgressBarsValues(Worker.ProgressEvent event) {
		int progressValue = (int)event.getPercentage();
		scraperControls.setProgressBarValue(progressValue);
	}

	public void onWorkerDone() {
		scraperControls.resetButtons();
		scraperControls.setStatusText(WORK_DONE_MESSAGE);
		scraperControls.setProgressBarValue(100);
	}

}
