package scraper.core.scrapers;

import scraper.core.Document;
import scraper.core.writers.BasicFileWriter;

public class ClaimsScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Claims";

	private String claims;

	public ClaimsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String selector = HasClaimsScraper.getClaimsSectionSelector();
		claims = selectFirst(selector).wholeText();

		boolean areClaimsEmpty = claims.isEmpty();
		if (areClaimsEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
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
