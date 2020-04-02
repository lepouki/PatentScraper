package tests.groups;

import scraper.core.*;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import scraper.core.logs.sinks.ConsoleSink;
import scraper.loggers.DocumentProgressLogger;
import scraper.loggers.PropertyProgressLogger;
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
			new DocumentProgressEventCountTest(),
			new PropertyProgressEventCountTest(),
			new DocumentProgressLoggerTest(),
			new PropertyProgressLoggerTest()
		};
	}

	private class DocumentProgressEventCountTest extends Test implements EventListener {

		private int documentProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenAnEventListenerCountingTheNumberOfDocumentProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOneDocumentProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.DocumentProcessed) {
				++documentProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenAnEventListenerCountingTheNumberOfDocumentProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneDocumentProgressEventMustOccur() throws AssertionException {
			assertCondition(
				documentProgressEventCount == 1,
				"Scraper DocumentProgressEventCountTest failed"
			);
		}

	}

	private class PropertyProgressEventCountTest extends Test implements EventListener {

		int propertyProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenAnEventListenerCountingTheNumberOfPropertyProgressEvents();

			whenScrapingTheDocument();

			thenOnlyOnePropertyProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.PropertyProcessed) {
				++propertyProgressEventCount;
			}
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<PropertyScraper> propertyScrapers = new ArrayList<>(1);

			PropertyScraper dummyPropertyScraper = new DummyPropertyScraper();
			propertyScrapers.add(dummyPropertyScraper);

			scraper.setPropertyScrapers(propertyScrapers);
		}

		private void givenAnEventListenerCountingTheNumberOfPropertyProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOnePropertyProgressEventMustOccur() throws AssertionException {
			assertCondition(
				propertyProgressEventCount == 1,
				"Scraper PropertyProgressEventCountTest failed"
			);
		}

	}

	private class DocumentProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneConsoleDocumentProgressLoggerListeningToTheScraper();

			seeConsoleForDocumentProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneConsoleDocumentProgressLoggerListeningToTheScraper() {
			DocumentProgressLogger consoleDocumentProgressLogger = new DocumentProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consoleDocumentProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consoleDocumentProgressLogger);
		}

		private void seeConsoleForDocumentProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private class PropertyProgressLoggerTest extends Test {

		@Override
		public void run() {
			givenOneSingleDocumentToScrape();
			givenOneSingleScrapingStep();
			givenOneConsolePropertyProgressLoggerListeningToTheScraper();

			seeConsoleForPropertyProgressOutput();
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document dummyDocument = new DummyDocument();
			documents.add(dummyDocument);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleScrapingStep() {
			List<PropertyScraper> propertyScrapers = new ArrayList<>(1);

			PropertyScraper dummyPropertyScraper = new DummyPropertyScraper();
			propertyScrapers.add(dummyPropertyScraper);

			scraper.setPropertyScrapers(propertyScrapers);
		}

		private void givenOneConsolePropertyProgressLoggerListeningToTheScraper() {
			PropertyProgressLogger consolePropertyProgressLogger = new PropertyProgressLogger();

			ConsoleSink consoleSink = new ConsoleSink();
			consolePropertyProgressLogger.pushSink(consoleSink);

			scraper.pushEventListener(consolePropertyProgressLogger);
		}

		private void seeConsoleForPropertyProgressOutput() {
			scraper.scrapeDocuments();
		}

	}

	private static class DummyPropertyScraper extends PropertyScraper {

		public DummyPropertyScraper() {
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
