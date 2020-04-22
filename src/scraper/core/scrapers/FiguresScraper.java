package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.BasicFileWriter;

import java.io.IOException;

public class FiguresScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Figures";

	public FiguresScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);

		setFileWriter(
			new BasicFileWriter()
		);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Elements figureElements = select("li[itemprop=images]");

		for (Element figureElement : figureElements) {
			String figureLink = figureElement.selectFirst("meta[itemprop=full]").attr("content");
			setFileWriterFileForDocumentFigure(document, figureLink);
			writeFigureDataToFileWriter(figureLink);
		}
	}

	private void setFileWriterFileForDocumentFigure(Document document, String figureLink) {
		String figureName = getFigureNameInFigureLink(figureLink);
		String figureRelativeFilePath = String.format("extra/figures/%s/%s", document.identifier, figureName);
		setFileWriterFile(figureRelativeFilePath);
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
