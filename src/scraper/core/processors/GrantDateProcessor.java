package scraper.core.processors;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.Document;

public class GrantDateProcessor extends SinglePropertyPageProcessor {

	private static final String PROPERTY_NAME = "grant date";

	private String grantDate;

	public GrantDateProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
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
	protected String getPropertyValue() {
		return grantDate;
	}

}
