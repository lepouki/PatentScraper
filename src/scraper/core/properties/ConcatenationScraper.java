package scraper.core.properties;

import scraper.core.ScraperPaths;
import scraper.core.*;
import scraper.core.writers.BasicFileWriter;

public class ConcatenationScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Concatenate";

	private String concatenated;
	private ConcatenationOptions options;

	public ConcatenationScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	public void setConcatenationOptions(ConcatenationOptions options) {
		this.options = options;
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

		if (options.concatenateTitle) {
			concatenated += retrieveTitle() + '\n';
		}

		if (options.concatenateAbstract) {
			concatenated += retrieveAbstract();
		}

		if (options.concatenateDescription) {
			concatenated += retrieveDescription();
		}

		if (options.concatenateClaims) {
			concatenated += retrieveClaims();
		}
	}

	private String retrieveTitle() {
		String titleSelector = TitleScraper.getTitleSelector();
		return retrieveTextElement(titleSelector);
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
		setRelativeFileWriterFile(ScraperPaths.CONCATENATED_TEXT_DIRECTORY + document.identifier + ".txt");
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {concatenated};
	}

}
