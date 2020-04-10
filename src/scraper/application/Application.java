package scraper.application;

import scraper.application.groups.*;
import scraper.core.*;

import java.util.*;
import javax.swing.*;

public class Application extends JFrame {

	private static final String TITLE = "Scraper";
	private static final String WORK_DONE_MESSAGE = "Done";

	private InputOutputChooser inputOutputChooser;
	private ScraperOptionsPicker scraperOptionsPicker;
	private ScraperControls scraperControls;

	private Worker worker;
	private final WorkerProgressStringMaker workerProgressStringMaker;

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
		updateScraperControlsWorkerStarting();
		runWorker();
	}

	private void updateScraperControlsWorkerStarting() {
		scraperControls.toggleButtons();
		scraperControls.setProgressBarsValue(0);
	}

	private void runWorker() {
		Set<Document> documents = getDocuments();
		worker = new Worker(this, createScraper(), documents);
		worker.execute();
	}

	private Scraper createScraper() {
		List<PropertyScraper> propertyScrapers = scraperOptionsPicker.getPropertyScrapers();
		return new Scraper(propertyScrapers);
	}

	private Set<Document> getDocuments() {
		Set<Document> documents = new HashSet<>();

		Document document = new Document();
		document.pageLink = "https://patents.google.com/patent/EP2484415A1/en";
		document.identifier = "EP2484415A1";

		documents.add(document);

		return documents;
	}

	public void onAbortButtonPressed() {
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
