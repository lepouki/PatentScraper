package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.Document;

public class GrantDateScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Grant date";

	private String grantDate;

	public GrantDateScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {READABLE_NAME};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		Elements events = select("dd[itemprop=events]");
		grantDate = getGrantDateInEvents(events);
	}

	private String getGrantDateInEvents(Elements events) throws NoSuchPropertyException {
		for (Element event : events) {
			boolean eventTypeGranted = getEventType(event).equals("granted");

			if (eventTypeGranted) {
				return getEventDate(event);
			}
		}

		throw new NoSuchPropertyException();
	}

	private String getEventType(Element event) {
		return event.selectFirst("span[itemprop=type]").ownText();
	}

	private String getEventDate(Element event) {
		return event.selectFirst("time[itemprop=date]").ownText();
	}

	@Override
	protected String[] getPropertyData() {
		return new String[] {grantDate};
	}

}
