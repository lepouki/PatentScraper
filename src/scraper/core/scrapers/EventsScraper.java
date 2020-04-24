package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.*;

public class EventsScraper extends FileChangingPagePropertyScraper {

	private static final String READABLE_NAME = "Legal events";

	private final List<Event> events;

	public EventsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
		createFileWriter();
		events = new ArrayList<>();
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
	public void processDocument(Document document) throws NoSuchPropertyException {
		events.clear();
		Elements eventElements = select("tr[itemprop=legalEvents]");
		setFileWriterFileForDocument(document);

		for (Element eventElement : eventElements) {
			Event event = parseEvent(eventElement);
			events.add(event);
		}
	}

	private void setFileWriterFileForDocument(Document document) {
		setFileWriterFile("/extra/events/" + document.identifier + ".csv");
	}

	private Event parseEvent(Element legalEventElement) {
		String code = legalEventElement.selectFirst("td[itemprop=code]").ownText();
		String title = legalEventElement.selectFirst("td[itemprop=title]").ownText();
		String date = legalEventElement.selectFirst("time[itemprop=date]").ownText();
		String description = legalEventElement.selectFirst("td:last-of-type").text();
		return new Event(code, title, date, description);
	}

	@Override
	public String[] getPropertyData() {
		int columnCount = getColumnNames().length;
		int eventCount = events.size();
		String[] propertyData = new String[eventCount * columnCount];

		for (int i = 0; i < eventCount; ++i) {
			Event event = events.get(i);
			int index = i * columnCount;
			pushLegalEventToPropertyData(event, index, propertyData);
		}

		return propertyData;
	}

	private void pushLegalEventToPropertyData(Event event, int index, String[] propertyData) {
		propertyData[index] = event.date;
		propertyData[index + 1] = event.code;
		propertyData[index + 2] = event.title;
		propertyData[index + 3] = event.description;
	}

}
