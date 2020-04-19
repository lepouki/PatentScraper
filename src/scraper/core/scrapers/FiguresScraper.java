package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.Document;
import scraper.core.PageDownloader;
import scraper.core.writers.BasicFileWriter;

import java.io.File;
import java.io.IOException;

public class FiguresScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Figures";

	private String rootDirectory;

	public FiguresScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public void initialize(String rootDirectory) {
		setFileWriter(
			new BasicFileWriter()
		);

		this.rootDirectory = rootDirectory;
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Elements figureElements = select("li[itemprop=images]");

		for (Element figureElement : figureElements) {
			String figureLink = figureElement.selectFirst("meta[itemprop=full]").attr("content");
			setFileDataWriterFileForDocumentFigure(document, figureLink);
			writeFigureDataToFileWriter(figureLink);
		}
	}

	private void setFileDataWriterFileForDocumentFigure(Document document, String figureLink) {
		String figureName = getFigureNameInFigureLink(figureLink);
		String figureRelativeFilePath = String.format("extra/figures/%s/%s", document.identifier, figureName);
		setFileWriterFile(rootDirectory + File.separator + figureRelativeFilePath);
	}

	private String getFigureNameInFigureLink(String figureLink) {
		String separator = Character.toString('/');
		String[] splitFigureLink = figureLink.split(separator);
		return splitFigureLink[splitFigureLink.length - 1];
	}

	private void writeFigureDataToFileWriter(String figureLink) {
		try {
			String figureData = PageDownloader.retrieveBinary(figureLink);
			writeBinaryToFileWriter(figureData);
		}
		catch (IOException ignored) {}
	}

}
