package tests.groups;

import scraper.core.Document;
import scraper.core.OutputTarget;
import scraper.core.Scraper;
import scraper.core.ScrapingStep;
import scraper.core.events.Event;
import scraper.core.events.EventListener;
import tests.Test;
import tests.TestGroup;

import java.util.ArrayList;
import java.util.List;

public class ScraperTestGroup implements TestGroup {

	private Scraper scraper;

	@Override
	public void initializeStateForNextTest() {
		scraper = new Scraper();
	}

	@Override
	public Test[] getTests() {
		return new Test[] {
			new ScrapingProgressEventCountTest(),
			new ScrapingStepProgressEventCountTest()
		};
	}

	private class ScrapingProgressEventCountTest extends Test implements EventListener {

		private int scrapingProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenAnEventListenerCountingTheNumberOfScrapingProgressEvents();
			givenOneSingleDocumentToScrape();

			whenScrapingTheDocument();

			thenOnlyOneScrapingProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.ScrapingProgressEvent) {
				++scrapingProgressEventCount;
			}
		}

		private void givenAnEventListenerCountingTheNumberOfScrapingProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document document = new Document();
			documents.add(document);

			scraper.setDocuments(documents);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScrapingProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scrapingProgressEventCount == 1,
				"Scraper ScrapingProgressEventCountTest failed."
			);
		}

	}

	private class ScrapingStepProgressEventCountTest extends Test implements EventListener {

		int scrapingStepProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenAnEventListenerCountingTheNumberOfScrapingStepProgressEvents();
			givenOneSingleDocumentToScrape();
			givenOneSingleDocumentScrapingStep();

			whenScrapingTheDocument();

			thenOnlyOneScrapingStepProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.ScrapingStepProgressEvent) {
				++scrapingStepProgressEventCount;
			}
		}

		private void givenAnEventListenerCountingTheNumberOfScrapingStepProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void givenOneSingleDocumentToScrape() {
			List<Document> documents = new ArrayList<>(1);

			Document document = new Document();
			documents.add(document);

			scraper.setDocuments(documents);
		}

		private void givenOneSingleDocumentScrapingStep() {
			List<ScrapingStep> scrapingSteps = new ArrayList<>(1);

			OutputTarget outputTarget = new DummyOutputTarget();
			ScrapingStep scrapingStep = new ScrapingStep(outputTarget);
			scrapingSteps.add(scrapingStep);

			scraper.setScrapingSteps(scrapingSteps);
		}

		private void whenScrapingTheDocument() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScrapingStepProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scrapingStepProgressEventCount == 1,
				"Scraper ScrapingStepProgressEventCountTest failed."
			);
		}

	}

	private static class DummyOutputTarget implements OutputTarget {

		@Override
		public void write(String information) {
		}

	}

}
