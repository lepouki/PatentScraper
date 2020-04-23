package scraper.core.scrapers;

import scraper.core.Document;

import java.io.File;

public class IdentifierScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Identifier";
	private static final String RELATIVE_DATA_FRAME_CSV_PATH = "csv" + File.separator + "Data frame.csv";

	private String identifier;

	public IdentifierScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriterFile(rootDirectory + File.separator + RELATIVE_DATA_FRAME_CSV_PATH);
	}

	@Override
	public void cleanupResources() {
		closeFileWriter();
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"identifier"};
	}

	@Override
	public void processDocument(Document document) {
		identifier = retrieveIdentifier();
	}

	private String retrieveIdentifier() {
		String selector = isPatent()
			? "dd[itemprop=publicationNumber]"
			: "dd[itemprop=docID]";

		return retrieveElementText(selector);
	}

	private boolean isPatent() {
		try {
			selectFirst("dd[itemprop=publicationNumber]");
			return true;
		}
		catch (NoSuchPropertyException exception) {
			return false;
		}
	}

	private String retrieveElementText(String selector) {
		try {
			return selectFirst(selector).ownText();
		}
		catch (NoSuchPropertyException ignored) {
			return "";
		}
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {identifier};
	}

}
