package scraper.core.properties;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.PropertyScraper;

public class PagePropertyScraper extends PropertyScraper {

	private final PageScraper pageScraper;

	public PagePropertyScraper(String readableName, PageScraper pageScraper) {
		super(readableName);
		this.pageScraper = pageScraper;
	}

	protected String getPageLink() {
		return pageScraper.getPageLink();
	}

	protected Elements select(String selector) throws NoSuchPropertyException {
		Elements selected = pageScraper.getPage().select(selector);

		if (selected.size() == 0) {
			throw new NoSuchPropertyException();
		}

		return selected;
	}

	protected Element selectFirst(String selector) throws NoSuchPropertyException {
		Element selected = pageScraper.getPage().selectFirst(selector);

		if (selected == null) {
			throw new NoSuchPropertyException();
		}

		return selected;
	}

}
