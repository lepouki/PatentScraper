package scraper.core.scrapers;

import scraper.core.ScraperPaths;
import scraper.core.*;
import scraper.core.writers.BasicFileWriter;

import java.io.*;

public class PdfScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "PDF";

	private String pdfContent;

	public PdfScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String pdfLink = selectFirst("a[itemprop=pdfLink]").attr("href");
		tryRetrievePdf(pdfLink);

		boolean isPdfEmpty = pdfContent.isEmpty();
		if (isPdfEmpty) {
			throw new NoSuchPropertyException();
		}

		setOutputFileForDocument(document);
	}

	private void tryRetrievePdf(String pdfLink) {
		try {
			pdfContent = PageDownloader.retrieveBinary(pdfLink);
		}
		catch (IOException exception) {
			pdfContent = "";
		}
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile(ScraperPaths.PDF_DIRECTORY + document.identifier + ".pdf");
	}

	@Override
	public String[] getPropertyData() {
		return new String[] {pdfContent};
	}

	@Override
	public boolean isPropertyDataBinary() {
		return true;
	}

}
