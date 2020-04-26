package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class EventsScraper extends CsvConvertiblePagePropertyScraper {

	private static final String READABLE_NAME = "Legal events";

	public EventsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
		createFileWriter();
	}

	private void createFileWriter() {
		CsvFileWriter csvFileWriter = new CsvFileWriter();
		setFileWriter(csvFileWriter);
		setFileWriterColumnNames(csvFileWriter);
	}

	private void setFileWriterColumnNames(CsvFileWriter fileWriter) {
		String[] columnNames = getColumnNames();

		fileWriter.setColumnNames(
			Arrays.asList(columnNames)
		);
	}

	private static String[] getColumnNames() {
		return new String[] {"date", "code", "title", "description"};
	}

	@Override
	protected void processProperties(Document document) throws NoSuchPropertyException {
		Elements eventElements = select("tr[itemprop=legalEvents]");
		setFileWriterFileForDocument(document);

		for (Element eventElement : eventElements) {
			pushProperty(
				parseEvent(eventElement)
			);
		}
	}

	private void setFileWriterFileForDocument(Document document) {
		setRelativeFileWriterFile("extra/events/" + document.identifier + ".csv");
	}

	private Event parseEvent(Element eventElement) {
		String code = eventElement.selectFirst("td[itemprop=code]").ownText();
		String title = eventElement.selectFirst("td[itemprop=title]").ownText();
		String date = eventElement.selectFirst("time[itemprop=date]").ownText();
		String description = eventElement.selectFirst("td:last-of-type").text();
		return new Event(code, title, date, description);
	}

}
