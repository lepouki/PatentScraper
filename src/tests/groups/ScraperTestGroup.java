package tests.groups;

import scraper.core.Document;
import scraper.core.WriteTarget;
import scraper.core.Scraper;
import scraper.core.ScrapingStep;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.sinks.ConsoleSink;
import scraper.loggers.ScrapingProgressLogger;
import scraper.loggers.ScrapingStepProgressLogger;
import tests.Test;
import tests.TestGroup;

import java.util.ArrayList;
import java.util.List;

public class ScraperTestGroup implements TestGroup {

	private Scraper scraper;

	@Override
	public void beginNextTest() {
		scraper = Scraper.createEmptyScraper();
	}

	@Override
	public Test[] getTests() {
		return new Test[] {
			new ScrapingProgressEventCountTest(),
			new ScrapingStepProgressEventCountTest(),
			new ScrapingProgressLoggerTest(),
			new ScrapingStepProgressLoggerTest()
		};
	}

	private class ScrapingProgressEventCountTest extends Test implements EventListener {

		private int scrapingProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenAnEventListenerCountingTheNumberOfScrapingProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOneScrapingProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.ScrapingProgressEvent) {
				++scrapingProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenAnEventListenerCountingTheNumberOfScrapingProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScrapingProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scrapingProgressEventCount == 1,
				"Scraper ScrapingProgressEventCountTest failed"
			);
		}

	}

	private class ScrapingStepProgressEventCountTest extends Test implements EventListener {

		int scrapingStepProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenAnEventListenerCountingTheNumberOfScrapingStepProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOneScrapingStepProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.ScrapingStepProgressEvent) {
				++scrapingStepProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<ScrapingStep> scrapingSteps = new ArrayList<>(1);

			ScrapingStep dummyScrapingStep = new DummyScrapingStep();
			scrapingSteps.add(dummyScrapingStep);

			scraper.setScrapingSteps(scrapingSteps);
		}

		private void givenAnEventListenerCountingTheNumberOfScrapingStepProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScrapingStepProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scrapingStepProgressEventCount == 1,
				"Scraper ScrapingStepProgressEventCountTest failed"
			);
		}

	}

	private class ScrapingProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneConsoleScrapingProgressLoggerListeningToTheScraper();

			seeConsoleForScrapingProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneConsoleScrapingProgressLoggerListeningToTheScraper() {
			ScrapingProgressLogger consoleScrapingProgressLogger = new ScrapingProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consoleScrapingProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consoleScrapingProgressLogger);
		}

		private void seeConsoleForScrapingProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private class ScrapingStepProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenOneConsoleScrapingStepProgressLoggerListeningToTheScraper();

			seeConsoleForScrapingStepProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<ScrapingStep> scrapingSteps = new ArrayList<>(1);

			ScrapingStep dummyScrapingStep = new DummyScrapingStep();
			scrapingSteps.add(dummyScrapingStep);

			scraper.setScrapingSteps(scrapingSteps);
		}

		private void givenOneConsoleScrapingStepProgressLoggerListeningToTheScraper() {
			ScrapingStepProgressLogger consoleScrapingStepProgressLogger = new ScrapingStepProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consoleScrapingStepProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consoleScrapingStepProgressLogger);
		}

		private void seeConsoleForScrapingStepProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private static class DummyScrapingStep extends ScrapingStep {

		public DummyScrapingStep() {
			super(new DummyWriteTarget(), "Dummy scraping step");
		}

	}

	private static class DummyWriteTarget implements WriteTarget {

		@Override
		public void write(String information) {
		}

	}

	private static class DummyDocument extends Document {

		public DummyDocument() {
			identifier = "Dummy document";
		}

	}

}
