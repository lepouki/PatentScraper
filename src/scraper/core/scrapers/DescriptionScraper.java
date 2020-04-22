package scraper.core.scrapers;

import scraper.core.Document;
import scraper.core.writers.BasicFileWriter;

public class DescriptionScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Description";

	private String description;

	public DescriptionScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		description = selectFirst("section[itemprop=description]").wholeText();

		boolean isDescriptionEmpty = description.isEmpty();
		if (isDescriptionEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
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
