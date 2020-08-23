package scraper.core.properties;

import org.jsoup.select.Elements;
import scraper.core.Document;

public class InventorsOrAuthorsScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Inventors or authors";

	private String inventorsOrAuthors;

	public InventorsOrAuthorsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String selector = getInventorsSelector();
		Elements inventorElements = select(selector);
		inventorsOrAuthors = makeInventorsOrAuthorsString(inventorElements);
	}

	private String makeInventorsOrAuthorsString(Elements inventorElements) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < inventorElements.size(); ++i) {
			String inventorNameText = inventorElements.get(i).ownText();
			builder.append(inventorNameText);

			if (i < inventorElements.size() - 1) {
				builder.append(',');
			}
		}

		return builder.toString();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {inventorsOrAuthors};
	}

	public static String getInventorsSelector() {
		return "dd[itemprop=inventor]";
	}

}
