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
			events.add(
				parseEvent(eventElement)
			);
		}
	}

	private void setFileWriterFileForDocument(Document document) {
		setRelativeFileWriterFile("/extra/events/" + document.identifier + ".csv");
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
		int entryCount = getColumnNames().length * events.size();
		List<String> entries = new ArrayList<>(entryCount);

		for (Event event : events) {
			pushEventToEntries(event, entries);
		}

		return CsvConvertiblePagePropertyScraper.toEntryArray(entries);
	}

	private void pushEventToEntries(Event event, List<String> entries) {
		List<String> eventEntries = Arrays.asList(
			event.toCsvEntries()
		);

		entries.addAll(eventEntries);
	}

}
