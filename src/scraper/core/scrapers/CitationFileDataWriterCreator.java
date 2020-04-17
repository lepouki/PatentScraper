package scraper.core.scrapers;

import scraper.core.PropertyScraper;

import java.io.File;

public class CitationFileDataWriterCreator extends PropertyScraper {

	private static final String READABLE_NAME = "Citation file data writer";
	private static final String RELATIVE_CITATION_CSV_PATH = "csv" + File.separator + "Citations.csv";

	public CitationFileDataWriterCreator() {
		super(READABLE_NAME);
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileDataWriterFile(rootDirectory + File.separator + RELATIVE_CITATION_CSV_PATH);
	}

	@Override
	public void cleanup() {
		closeFileDataWriter();
	}

}
