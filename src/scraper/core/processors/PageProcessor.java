package scraper.core.processors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

public class PageProcessor extends PropertyProcessor {

	private static final String PROPERTY_NAME = "Online page";
	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private String pageLink = "";
	private org.jsoup.nodes.Document document;

	public PageProcessor() {
		super(PROPERTY_NAME);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		pageLink = makePageLink(document);
		tryRetrievePage();
	}

	private String makePageLink(Document document) {
		return PATENT_LINK_PREFIX + document.identifier;
	}

	private void tryRetrievePage() throws NoSuchPropertyException {
		try {
			document = Jsoup.connect(pageLink).get();
		}
		catch (Throwable ignored) {
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
