package tests.groups;

import scraper.core.*;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
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
			new ItemProgressEventCountTest()
		};
	}

	private class ItemProgressEventCountTest extends Test implements EventListener {

		int propertyProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneSingleScrapingStep();
			givenAnEventListenerCountingTheNumberOfPropertyProgressEvents();

			whenScrapingOneDocument();

			thenOnlyOnePropertyProgressEventMustOccur();
		}

		@Override
		public void onEventReceived(Event event) {
			if (event instanceof Scraper.PropertyProcessed) {
				++propertyProgressEventCount;
			}
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

		private void whenScrapingOneDocument() {
			Document dummyDocument = new DummyDocument();
			scraper.scrapeDocument(dummyDocument);
		}

		private void thenOnlyOnePropertyProgressEventMustOccur() throws AssertionException {
			assertCondition(
				propertyProgressEventCount == 1,
				"Scraper PropertyProgressEventCountTest failed"
			);
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
