package scraper.loggers;

import scraper.core.Scraper;
import scraper.core.logs.Logger;

public class ProgressLogger extends Logger {

	private String prefix;

	public ProgressLogger(String prefix) {
		this.prefix = prefix;
	}

	public void logProgress(Scraper.Progress progress) {
		logPercentage(progress);
		logLastElementProcessed(progress);
	}

	public void logPrefix() {
		log(prefix);
	}

	private void logPercentage(Scraper.Progress progress) {
		float percentage = progress.getPercentage();
		log("%.2f%%\n", percentage);
	}

	private void logLastElementProcessed(Scraper.Progress progress) {
		String lastElementProcessed = progress.getLastElementProcessed();

		logPrefix();
		log("Last element processed: %s\n", lastElementProcessed);
	}

}
