package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String WORKER_DONE_MESSAGE = "Done";

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
			Set<Document> documents = getInputDocuments();
			List<PropertyScraper> propertyScrapers = getPropertyScrapers();
			int layerCount = scraperOptionsPicker.getLayerCount();
			workerManager.runWorker(documents, propertyScrapers, layerCount);
		}
		catch (IOException exception) {
			statusMessageUpdater.handleException(exception);
		}
	}

	public void onWorkerInitialized() {
		scraperControls.toggleButtons();
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

	public void onWorkerProgress(ProgressEvent event) {
		updateProgressBarsText(event);
		updateProgressBarsValues(event);
	}

	private void updateProgressBarsText(ProgressEvent event) {
		statusMessageUpdater.handleProgressEvent(event);
	}

	private void updateProgressBarsValues(ProgressEvent event) {
		int progressValue = (int)event.getPercentage();

		if (event instanceof Scraper.LayerProgressEvent) {
			scraperControls.setLayerProgressBarValue(progressValue);
		}
		else if (event instanceof Scraper.DocumentProgressEvent) {
			scraperControls.setDocumentProgressBarValue(progressValue);
		}
	}

	public void onWorkerDone() {
		scraperControls.resetButtons();
		statusMessageUpdater.setStatusMessage(WORKER_DONE_MESSAGE);
		scraperControls.setProgressBarsValue(100);
	}

}
