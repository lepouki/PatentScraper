package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String WORK_DONE_MESSAGE = "Done";

	private final WorkerManager workerManager;
	private final StatusMessageUpdater statusMessageUpdater;

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	public Application() {
		super(TITLE);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		workerManager = new WorkerManager(this);
		createGroups();
		statusMessageUpdater = new StatusMessageUpdater(scraperControls);

		configureFrame();
	}

	private void createGroups() {
		inputOutputChooser = new InputOutputChooser();
		add(inputOutputChooser);

		scraperOptionsPicker = new ScraperOptionsPicker(workerManager);
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
			Set<Document> documents = getInputDocuments();
			List<PropertyScraper> propertyScrapers = getPropertyScrapers();
			workerManager.runWorker(documents, propertyScrapers);
		}
		catch (IOException exception) {
			statusMessageUpdater.handleException(exception);
		}
	}

	public void onWorkerInitialized() {
		scraperControls.toggleButtons();
		scraperControls.setProgressBarValue(0);
	}

	private Set<Document> getInputDocuments() throws IOException {
		String inputFilePath = inputOutputChooser.getInputFilePathText();
		PathChecker.checkExists(inputFilePath);
		return inputOutputChooser.getCsvParser().parseFile(inputFilePath);
	}

	private List<PropertyScraper> getPropertyScrapers() throws IOException {
		String outputDirectoryPath = inputOutputChooser.getOutputDirectoryPathText();
		PathChecker.checkExists(outputDirectoryPath);
		return scraperOptionsPicker.getPropertyScrapers(outputDirectoryPath);
	}

	public void onAbortButtonPressed() {
		abort();
	}

	public void abort() {
		workerManager.abortWorker();
	}

	public void onWorkerProgressMade(ProgressEvent event) {
		updateProgressBarsText(event);
		updateProgressBarsValues(event);
	}

	private void updateProgressBarsText(ProgressEvent event) {
		scraperControls.setStatus(
			event.getStatus()
		);
	}

	private void updateProgressBarsValues(ProgressEvent event) {
		int progressValue = (int)event.getPercentage();
		scraperControls.setProgressBarValue(progressValue);
	}

	public void onWorkerDone() {
		scraperControls.resetButtons();
		workerManager.closePropertyScraperFileWriters();
		statusMessageUpdater.setMessage(WORK_DONE_MESSAGE);
		scraperControls.setProgressBarValue(100);
	}

}
