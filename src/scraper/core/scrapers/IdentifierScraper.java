package scraper.core.scrapers;

import scraper.application.ScraperPaths;
import scraper.core.Document;

public class IdentifierScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Identifier";

	private String identifier;

	public IdentifierScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriterFile(rootDirectory + '/' + ScraperPaths.DATA_FRAME_PATH);
	}

	@Override
	public void cleanupResources() {
		closeFileWriter();
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
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
