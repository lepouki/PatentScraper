package scraper.core.processors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class PageProcessor extends PropertyProcessor {

	private static final String PROPERTY_NAME = "Online page";
	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private org.jsoup.nodes.Document document;

	public PageProcessor() {
		super(PROPERTY_NAME);
	}

	@Override
	public void initializeForNextLayer() {

	}

	@Override
	public void processDocument(Document document) {
		String pageLink = PATENT_LINK_PREFIX + document.identifier;
		tryRetrievePage(pageLink);
	}

	private void tryRetrievePage(String link) {
		try {
			document = Jsoup.connect(link).get();
		}
		catch (IOException ignored) {}
	}

	public Element getPage() {
		return document;
	}

	@Override
	public String retrievePropertyData() {
		return "";
	}

}
