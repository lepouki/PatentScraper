package scraper.core.scrapers;

import scraper.core.PropertyScraper;
import scraper.core.processors.DummyProcessor;

import java.io.File;
import java.io.IOException;

public class CitationFileDataWriterCreator extends PropertyScraper {

	private static final String RELATIVE_CITATION_CSV_PATH = "csv" + File.separator + "Citations.csv";

	public CitationFileDataWriterCreator() {
		super(
			new DummyProcessor()
		);
	}

	@Override
	public void initialize(String rootDirectory) throws IOException {
		setFileDataWriterFile(rootDirectory + File.separator + RELATIVE_CITATION_CSV_PATH);
	}

	@Override
	public void cleanup() throws IOException {
		closeFileDataWriter();
	}

}
