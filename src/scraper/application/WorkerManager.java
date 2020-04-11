package scraper.application;

import scraper.core.*;

import java.util.*;

public class WorkerManager {

	private final Application application;
	private Worker worker;
	private List<PropertyScraper> propertyScrapers;

	public WorkerManager(Application application) {
		this.application = application;
	}

	public void runWorker(Set<Document> documents, List<PropertyScraper> propertyScrapers) {
		this.propertyScrapers = propertyScrapers;
		createWorker(documents);
		worker.execute();
	}

	private void createWorker(Set<Document> documents) {
		worker = new Worker(
			application, new Scraper(documents, propertyScrapers)
		);

		application.onWorkerInitialized();
	}

	public void abortWorker() {
		boolean canAbort = (worker != null) && !worker.isCancelled() && !worker.isDone();

		if (canAbort) {
			worker.cancel(false);
		}
	}

	public void cleanupPropertyScrapers() {
		for (PropertyScraper propertyScraper : propertyScrapers) {
			propertyScraper.cleanup();
		}
	}

}
