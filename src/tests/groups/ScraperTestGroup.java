package tests.groups;

import scraper.core.Document;
import scraper.core.Scraper;
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
			new ScrapingProgressEventCountTest()
		};
	}

	private class ScrapingProgressEventCountTest extends Test implements EventListener {

		private int scrapingProgressEventCount = 0;

		@Override
		public void run() throws Exception {
			givenOneDocumentListContainingOneDocument();
			givenAnEventListenerCountingTheNumberOfScrapingProgressEvents();
			whenScrapingTheDocumentList();
			thenOnlyOneScrapingProgressEventMustOccur();
		}

		@Override
		public void eventReceived(Event event) {
			if (event instanceof Scraper.ScrapingProgressEvent) {
				++scrapingProgressEventCount;
			}
		}

		private void givenOneDocumentListContainingOneDocument() {
			List<Document> documents = new ArrayList<>(1);

			Document document = new Document();
			documents.add(document);

			scraper.setDocuments(documents);
		}

		private void givenAnEventListenerCountingTheNumberOfScrapingProgressEvents() {
			scraper.pushEventListener(this);
		}

		private void whenScrapingTheDocumentList() {
			scraper.scrapeDocuments();
		}

		private void thenOnlyOneScrapingProgressEventMustOccur() throws AssertionException {
			assertCondition(
				scrapingProgressEventCount == 1,
				"Scraper ScrapingProgressTest failed."
			);
		}

	}

}
