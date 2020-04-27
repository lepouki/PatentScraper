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
		String selector = getAbstractSelector();
		abstractDescription = selectFirst(selector).wholeText();

		boolean isAbstractDescriptionEmpty = abstractDescription.isEmpty();
		if (isAbstractDescriptionEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
	}

	public static String getAbstractSelector() {
		return "section[itemprop=abstract]";
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile("TEXT/ABSTRACT/" + document.identifier + ".txt");
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {abstractDescription};
	}

}
