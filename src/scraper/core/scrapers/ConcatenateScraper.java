package scraper.core.scrapers;

import scraper.core.Document;
import scraper.core.writers.BasicFileWriter;

public class ConcatenateScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Concatenated text";

	private String concatenated;

	public ConcatenateScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		concatenateDocumentText();

		boolean isTextEmpty = concatenated.isEmpty();
		if (isTextEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
	}

	private void concatenateDocumentText() {
		concatenated = "";
		concatenated += retrieveAbstract();
		concatenated += retrieveDescription();
		concatenated += retrieveClaims();
	}

	private String retrieveAbstract() {
		String abstractSelector = AbstractDescriptionScraper.getAbstractSelector();
		return retrieveTextElement(abstractSelector);
	}

	private String retrieveTextElement(String selector) {
		try {
			return selectFirst(selector).wholeText();
		}
		catch (NoSuchPropertyException exception) {
			return "";
		}
	}

	private String retrieveDescription() {
		String descriptionSelector = DescriptionScraper.getDescriptionSelector();
		return retrieveTextElement(descriptionSelector);
	}

	private String retrieveClaims() {
		String claimsSelector = ClaimsScraper.getClaimsSelector();
		return retrieveTextElement(claimsSelector);
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile("TEXT/CONCATENATED/" + document.identifier + ".txt");
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {concatenated};
	}

}
