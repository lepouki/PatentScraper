package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.IdentifierProcessor;
import scraper.core.processors.PageProcessor;

import java.io.File;
import java.io.IOException;

public class IdentifierScraper extends PropertyScraper {

	private static final String RELATIVE_DATA_FRAME_CSV_PATH = "csv" + File.separator + "DataFrame.csv";

	public IdentifierScraper(PageProcessor pageProcessor) {
		super(
			new IdentifierProcessor(pageProcessor)
		);
	}

	@Override
	public void initialize(String rootDirectory) throws IOException {
		setFileDataWriterFile(rootDirectory + File.separator + RELATIVE_DATA_FRAME_CSV_PATH);
	}

	@Override
	public void cleanup() throws IOException {
		closeFileDataWriter();
	}

}
