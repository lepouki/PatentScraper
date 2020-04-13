package scraper.application;

import scraper.core.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorkerManager {

	private final Application application;
	private Worker worker;

	public WorkerManager(Application application) {
		this.application = application;
	}

	public void runWorker(Set<Document> documents, List<PropertyScraper> propertyScrapers, int layerCount) {
		worker = new Worker(
			application, new Scraper(documents, propertyScrapers, layerCount)
		);

		application.onWorkerInitialized();
		worker.execute();
	}

	public void abortWorker() {
		boolean canAbort = (worker != null) && !worker.isCancelled() && !worker.isDone();

		if (canAbort) {
			worker.cancel(false);
		}
	}

	public long getNanosecondsElapsed() {
		try {
			return worker.get();
		}
		// This function only gets called when the worker is not running so this should never happen
		catch (Exception ignored) {}

		return 0;
	}

}
