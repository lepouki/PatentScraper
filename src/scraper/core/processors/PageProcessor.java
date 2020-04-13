package scraper.core.processors;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class PageProcessor extends PropertyProcessor {

	private static final String PROPERTY_NAME = "Online page";
	private static final String PATENT_LINK_PREFIX = "https://patents.google.com/patent/";

	private String pageLink = "";
	private org.jsoup.nodes.Document document;

	public PageProcessor() {
		super(PROPERTY_NAME);
	}

	@Override
	public void initializeForNextLayer() {
	}

	@Override
	public void processDocument(Document document) {
		pageLink = makePageLink(document);
		tryRetrievePage();
	}

	private String makePageLink(Document document) {
		return PATENT_LINK_PREFIX + removeIdentifierDashes(document.identifier);
	}

	private String removeIdentifierDashes(String identifier) {
		return identifier.replace("-", "");
	}

	private void tryRetrievePage() {
		try {
			document = Jsoup.connect(pageLink).get();
		}
		catch (IOException ignored) {}
	}

	@Override
	public String getPropertyData() {
		return "";
	}

	public Element getPage() {
		return document;
	}

	public String getPageLink() {
		return pageLink;
	}

}
