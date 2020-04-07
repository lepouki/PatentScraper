package scraper.application;

import scraper.application.widgets.*;
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
	private static final String WORK_DONE_MESSAGE = "Work done";

	private InputOutputChooser inputOutputChooser;
	private OptionsPicker optionsPicker;
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
		inputOutputChooser = new InputOutputChooser(ComponentConfiguration.PADDING);
		add(inputOutputChooser);

		optionsPicker = new OptionsPicker(ComponentConfiguration.PADDING);
		add(optionsPicker);

		scraperControls = new ScraperControls(this, ComponentConfiguration.PADDING);
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

		List<Document> documents = createDocuments();
		worker = new Worker(this, createScraper(), documents);
		worker.execute();
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

	private Scraper createScraper() {
		List<PropertyScraper> propertyScrapers = createPropertyScrapers();
		return new Scraper(propertyScrapers);
	}

	private List<PropertyScraper> createPropertyScrapers() {
		ScraperOptions scraperOptions = optionsPicker.getScraperOptions();
		return new ArrayList<>(0); // TODO
	}

	private List<Document> createDocuments() {
		return new ArrayList<>(0); // TODO
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
