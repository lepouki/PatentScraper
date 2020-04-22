package scraper.core.scrapers;

import scraper.core.Document;
import scraper.core.writers.BasicFileWriter;

public class AbstractDescriptionScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Abstract";

	private String abstractDescription;

	public AbstractDescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		abstractDescription = selectFirst("section[itemprop=abstract]").wholeText();

		boolean isAbstractDescriptionEmpty = abstractDescription.isEmpty();
		if (isAbstractDescriptionEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
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
