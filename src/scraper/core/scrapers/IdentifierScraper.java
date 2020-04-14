package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.IdentifierProcessor;

import java.io.File;

public class IdentifierScraper extends PropertyScraper {

	public IdentifierScraper() {
		super(
			new IdentifierProcessor()
		);
	}

	@Override
	public void initialize(String rootDirectory) {
		setDataWriterFile(rootDirectory + File.separator + "DataFrame.csv");
	}

	@Override
	public void cleanup() {
		closeDataWriter();
	}

}
