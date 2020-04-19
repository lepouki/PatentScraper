package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Patent scraper";
	private static final String WORKER_SCRAPING_MESSAGE = "Scraping";
	private static final String WORKER_DONE_MESSAGE = "Done";

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	private final WorkerManager workerManager;
	private final StatusMessageUpdater statusMessageUpdater;

	public Application() {
		super(TITLE);

		MainPane mainPane = new MainPane();
		setContentPane(mainPane);

		workerManager = new WorkerManager(this);
		createWidgetGroups();
		statusMessageUpdater = new StatusMessageUpdater(scraperControls);

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
		try {
			List<Document> documents = getInputDocuments();
			List<PropertyScraper> propertyScrapers = getPropertyScrapers();
			int layerCount = scraperOptionsPicker.getLayerCount();
			workerManager.runWorker(documents, propertyScrapers, layerCount);
		}
		catch (IOException exception) {
			statusMessageUpdater.handleException(exception);
		}
	}

	private List<Document> getInputDocuments() throws IOException {
		String inputFilePath = inputOutputChooser.getInputFilePathText();
		PathChecker.checkExists(inputFilePath);
		return inputOutputChooser.getCsvParser().parseFile(inputFilePath);
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
		int value = event.getValue();
		String status = event.getStatus();

		if (event instanceof Scraper.LayerProgressEvent) {
			updateLayerProgressBar(value, status);
		}
		else if (event instanceof Scraper.DocumentProgressEvent) {
			updateDocumentProgressBar(value, status);
		}
	}

	private void updateLayerProgressBar(int value, String text) {
		scraperControls.setLayerProgressBarText(text);
		scraperControls.setLayerProgressBarValue(value);
	}

	private void updateDocumentProgressBar(int value, String text) {
		scraperControls.setDocumentProgressBarText(text);
		scraperControls.setDocumentProgressBarValue(value);
	}

	public void onWorkerDone() {
		setSettingsEnabled(true);
		statusMessageUpdater.setStatusMessage(WORKER_DONE_MESSAGE);
		scraperControls.resetButtons();
		scraperControls.resetProgressBars();
	}

}
