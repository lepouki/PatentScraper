package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String WORKER_SCRAPING_MESSAGE = "Scraping";
	private static final String WORKER_DONE_MESSAGE = "Done";

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	private final WorkerManager workerManager;

	public Application() {
		super(TITLE);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		createWidgetGroups();
		workerManager = new WorkerManager(this);
		configureFrame();
	}

	private void createWidgetGroups() {
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
		tryRunWorker();
	}

	private void tryRunWorker() {
		try {
			runWorker();
		}
		catch (IOException exception) {
			String exceptionMessage = exception.getMessage();
			scraperControls.setStatus("Error: " + exceptionMessage);
		}
	}

	private void runWorker() throws IOException {
		List<Document> documents = getInputDocuments();
		List<PropertyScraper> propertyScrapers = getPropertyScrapers();
		int layerCount = scraperOptionsPicker.getLayerCount();
		workerManager.runWorker(documents, propertyScrapers, layerCount);
	}

	private List<Document> getInputDocuments() throws IOException {
		String inputFilePath = inputOutputChooser.getInputFilePathText();
		PathChecker.checkExists(inputFilePath);
		return inputOutputChooser.getCsvParser().parseDocuments(inputFilePath);
	}

	private List<PropertyScraper> getPropertyScrapers() throws IOException {
		String outputDirectoryPath = inputOutputChooser.getOutputDirectoryPathText();
		PathChecker.checkExists(outputDirectoryPath);
		return scraperOptionsPicker.getPropertyScrapers(outputDirectoryPath);
	}

	public void onWorkerInitialized() {
		setSettingsEnabled(false);
		scraperControls.setStatus(WORKER_SCRAPING_MESSAGE);
		scraperControls.toggleButtons();
	}

	private void setSettingsEnabled(boolean enabled) {
		inputOutputChooser.setEnabled(enabled);
		scraperOptionsPicker.setEnabled(enabled);
	}

	public void onAbortButtonPressed() {
		abort();
	}

	public void abort() {
		workerManager.abortWorker();
	}

	public void onWorkerProgress(ProgressEvent event) {
		if (event instanceof Scraper.LayerProgressEvent) {
			updateLayerProgressBar(event);
		}
		else if (event instanceof Scraper.DocumentProgressEvent) {
			updateDocumentProgressBar(event);
		}
	}

	private void updateLayerProgressBar(ProgressEvent event) {
		String status = event.getStatus();
		scraperControls.setLayerProgressBarText(status);

		int progressPercentage = event.getPercentage();
		scraperControls.setLayerProgressBarValue(progressPercentage);
	}

	private void updateDocumentProgressBar(ProgressEvent event) {
		String status = event.getStatus();

		scraperControls.setDocumentProgressBarText(
			makeDocumentProgressString(event.getValue(), event.getMaximumValue(), status)
		);

		int progressPercentage = event.getPercentage();
		scraperControls.setDocumentProgressBarValue(progressPercentage);
	}

	private String makeDocumentProgressString(int value, int maximumValue, String text) {
		return String.format("%s (%d/%d)", text, value, maximumValue);
	}

	public void onWorkerDone() {
		writeScraperSummary();
		updateInterfaceWorkerDone();
	}

	private void writeScraperSummary() {
		String summaryFile = inputOutputChooser.getOutputDirectoryPathText() + "/csv/Summary.csv";
		workerManager.writeScraperSummary(summaryFile);
	}

	private void updateInterfaceWorkerDone() {
		setSettingsEnabled(true);
		scraperControls.setStatus(WORKER_DONE_MESSAGE);
		scraperControls.resetButtons();
		scraperControls.resetProgressBars();
	}

}
