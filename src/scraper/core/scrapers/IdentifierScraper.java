package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.IdentifierProcessor;
import scraper.core.processors.PageProcessor;

import java.io.File;
import java.io.IOException;

public class IdentifierScraper extends PropertyScraper {

	public IdentifierScraper(PageProcessor pageProcessor) {
		super(
			new IdentifierProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) throws IOException {
		setDataWriterFile(rootDirectory + File.separator + "DataFrame.csv");
	}

	@Override
	public void cleanup() throws IOException {
		closeDataWriter();
	}

}
