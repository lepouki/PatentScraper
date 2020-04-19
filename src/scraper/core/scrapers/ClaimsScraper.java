package scraper.core.scrapers;

import scraper.core.Document;

public class ClaimsScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Claims";

	private String claims;

	public ClaimsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		setOutputFileForDocument(document);
		String selector = HasClaimsScraper.getClaimsSectionSelector();
		claims = selectFirst(selector).wholeText();
	}

	private void setOutputFileForDocument(Document document) {
		String filePath = makeFilePathForDocument(document);
		setFileWriterFile(filePath);
	}

	private String makeFilePathForDocument(Document document) {
		return String.format("extra/claims/%s.txt", document.identifier);
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {claims};
	}

}
