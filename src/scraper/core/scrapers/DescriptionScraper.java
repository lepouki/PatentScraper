package scraper.core.scrapers;

import scraper.core.Document;

public class DescriptionScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Description";

	private String description;

	public DescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		setOutputFileForDocument(document);
		description = selectFirst("section[itemprop=description]").wholeText();
	}

	private void setOutputFileForDocument(Document document) {
		String filePath = makeFilePathForDocument(document);
		setFileWriterFile(filePath);
	}

	private String makeFilePathForDocument(Document document) {
		return String.format("extra/description/%s.txt", document.identifier);
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {description};
	}

}
