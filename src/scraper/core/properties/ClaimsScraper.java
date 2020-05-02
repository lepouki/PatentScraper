package scraper.core.properties;

import scraper.core.ScraperPaths;
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
		String selector = getClaimsSelector();
		claims = selectFirst(selector).wholeText();

		boolean areClaimsEmpty = claims.isEmpty();
		if (areClaimsEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
	}

	public static String getClaimsSelector() {
		return "section[itemprop=claims]";
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile(ScraperPaths.CLAIMS_DIRECTORY + document.identifier + ".txt");
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {claims};
	}

}
