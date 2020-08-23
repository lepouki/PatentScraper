package scraper.core.properties;

import scraper.core.ScraperPaths;
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
		try {
			identifier = retrieveIdentifier();
		}
		catch (NoSuchPropertyException exception) {
			identifier = document.identifier;
		}
	}

	private String retrieveIdentifier() throws NoSuchPropertyException {
		String selector = isPatent()
			? getPublicationNumberSelector()
			: "dd[itemprop=docID]";

		return retrieveElementText(selector);
	}

	private static String getPublicationNumberSelector() {
		return "dd[itemprop=publicationNumber]";
	}

	private boolean isPatent() {
		try {
			selectFirst(
				getPublicationNumberSelector()
			);

			return true;
		}
		catch (NoSuchPropertyException exception) {
			return false;
		}
	}

	private String retrieveElementText(String selector) throws NoSuchPropertyException {
		return selectFirst(selector).ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {identifier};
	}

}
