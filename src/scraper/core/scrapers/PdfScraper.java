package scraper.core.scrapers;

import scraper.core.*;
import scraper.core.writers.BasicFileDataWriter;

import java.io.*;

public class PdfScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "PDF";

	private String rootDirectory;
	private String pdfContent;

	public PdfScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileDataWriter(
			new BasicFileDataWriter()
		);
	}

	@Override
	public void initialize(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		setOutputFileForDocument(document);
		String pdfLink = selectFirst("a[itemprop=pdfLink]").attr("href");
		tryRetrievePdf(pdfLink);
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
		String filePath = makeFilePathForDocument(document);
		setFileDataWriterFile(filePath);
	}

	private String makeFilePathForDocument(Document document) {
		return String.format("%s/extra/pdf/%s.pdf", rootDirectory, document.identifier);
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
