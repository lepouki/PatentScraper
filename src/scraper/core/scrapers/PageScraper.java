package scraper.core.scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class PageScraper extends PropertyScraper {

	private static final String READABLE_NAME = "Document page";
	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private String pageLink;
	private org.jsoup.nodes.Document document;

	public PageScraper() {
		super(READABLE_NAME);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		this.document = null;
		pageLink = makePageLink(document);
		tryRetrievePage();
	}

	private String makePageLink(Document document) {
		return PATENT_LINK_PREFIX + document.identifier + "/en";
	}

	private void tryRetrievePage() throws NoSuchPropertyException {
		try {
			document = Jsoup.connect(pageLink).get();
		}
		catch (IOException exception) {
			document = new org.jsoup.nodes.Document("");
			throw new NoSuchPropertyException();
		}
	}

	public Element getPage() {
		return document;
	}

	public String getPageLink() {
		return pageLink;
	}

}
