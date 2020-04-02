package tests.groups;

import scraper.core.*;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.log.sinks.ConsoleSink;
import scraper.loggers.ScraperProgressLogger;
import scraper.loggers.DocumentScraperProgressLogger;
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
			new ScraperProgressEventCountTest(),
			new DocumentScraperProgressEventCountTest(),
			new ScraperProgressLoggerTest(),
			new DocumentScraperProgressLoggerTest()
		};
	}

	private class ScraperProgressEventCountTest extends Test implements EventListener {

		private int scraperProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenAnEventListenerCountingTheNumberOfScraperProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOneScraperProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event.getSource() instanceof Scraper) {
				++scraperProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenAnEventListenerCountingTheNumberOfScraperProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScraperProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scraperProgressEventCount == 1,
				"Scraper ScrapingProgressEventCountTest failed"
			);
		}

	}

	private class DocumentScraperProgressEventCountTest extends Test implements EventListener {

		int documentScraperProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenAnEventListenerCountingTheNumberOfDocumentScraperProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOneDocumentScraperProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event.getSource() instanceof DocumentScraper) {
				++documentScraperProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<PropertyWriter> propertyWriters = new ArrayList<>(1);

			PropertyWriter dummyPropertyWriter = new DummyPropertyWriter();
			propertyWriters.add(dummyPropertyWriter);

			scraper.setPropertyWriters(propertyWriters);
		}

		private void givenAnEventListenerCountingTheNumberOfDocumentScraperProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneDocumentScraperProgressEventMustOccur() throws AssertionException {
			assertCondition(
				documentScraperProgressEventCount == 1,
				"Scraper ScrapingStepProgressEventCountTest failed"
			);
		}

	}

	private class ScraperProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneConsoleScraperProgressLoggerListeningToTheScraper();

			seeConsoleForScraperProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneConsoleScraperProgressLoggerListeningToTheScraper() {
			ScraperProgressLogger consoleScraperProgressLogger = new ScraperProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consoleScraperProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consoleScraperProgressLogger);
		}

		private void seeConsoleForScraperProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private class DocumentScraperProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenOneConsoleDocumentScraperProgressLoggerListeningToTheScraper();

			seeConsoleForDocumentScraperProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<PropertyWriter> propertyWriters = new ArrayList<>(1);

			PropertyWriter dummyPropertyWriter = new DummyPropertyWriter();
			propertyWriters.add(dummyPropertyWriter);

			scraper.setPropertyWriters(propertyWriters);
		}

		private void givenOneConsoleDocumentScraperProgressLoggerListeningToTheScraper() {
			DocumentScraperProgressLogger consoleDocumentScraperProgressLogger = new DocumentScraperProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consoleDocumentScraperProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consoleDocumentScraperProgressLogger);
		}

		private void seeConsoleForDocumentScraperProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private static class DummyPropertyWriter extends PropertyWriter {

		public DummyPropertyWriter() {
			super(
				new DummyWriteTarget(),
				new DummyPropertyRetriever()
			);
		}

	}

	private static class DummyPropertyRetriever extends PropertyRetriever {

		public DummyPropertyRetriever() {
			super("Dummy document property");
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
