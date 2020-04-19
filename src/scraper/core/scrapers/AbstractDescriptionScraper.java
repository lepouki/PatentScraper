package scraper.core.scrapers;

import scraper.core.Document;

public class AbstractDescriptionScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Abstract";

	private String abstractDescription;

	public AbstractDescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		setOutputFileForDocument(document);
		abstractDescription = selectFirst("section[itemprop=abstract]").wholeText();
	}

	private void setOutputFileForDocument(Document document) {
		String filePath = makeFilePathForDocument(document);
		setFileWriterFile(filePath);
	}

	private String makeFilePathForDocument(Document document) {
		return String.format("extra/abstract/%s.txt", document.identifier);
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {abstractDescription};
	}

}
