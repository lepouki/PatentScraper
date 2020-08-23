package scraper.application;

import scraper.core.*;

import java.util.*;

public class WorkerManager {

	private final Application application;
	private Worker worker;

	public WorkerManager(Application application) {
		this.application = application;
	}

	public void runWorker(List<Document> documents, List<PropertyScraper> propertyScrapers, int layerCount) {
		worker = new Worker(
			application, new Scraper(documents, propertyScrapers, layerCount)
		);

		application.onWorkerInitialized();
		worker.execute();
	}

	public void abortWorker() {
		boolean canAbort = (worker != null) && !worker.isCancelled() && !worker.isDone();

		if (canAbort) {
			final boolean doInterrupt = true;
			worker.cancel(doInterrupt);
		}
	}

	public void writeScraperSummary(String filePath) {
		worker.writeScraperSummary(filePath);
	}

}
