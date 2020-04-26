package scraper.core.scrapers;

import scraper.core.*;

import java.util.*;

public abstract class CsvConvertiblePagePropertyScraper extends FileChangingPagePropertyScraper {

	private final List<CsvConvertible> properties;

	public CsvConvertiblePagePropertyScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
		properties = new ArrayList<>();
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		properties.clear();
		processProperties(document);
	}

	protected abstract void processProperties(Document document) throws NoSuchPropertyException;

	@Override
	public String[] getPropertyData() {
		List<String> entries = new ArrayList<>();

		for (CsvConvertible property : properties) {
			pushPropertyToEntries(property, entries);
		}

		return toEntryArray(entries);
	}

	private void pushPropertyToEntries(CsvConvertible property, List<String> entries) {
		List<String> propertyEntries = Arrays.asList(
			property.toCsvEntries()
		);

		entries.addAll(propertyEntries);
	}

	public static String[] toEntryArray(List<String> entries) {
		int entryCount = entries.size();
		String[] entriesArray = new String[entryCount];
		return entries.toArray(entriesArray);
	}

	protected void pushProperty(CsvConvertible property) {
		properties.add(property);
	}

	protected boolean containsProperty(CsvConvertible property) {
		return properties.contains(property);
	}

}
