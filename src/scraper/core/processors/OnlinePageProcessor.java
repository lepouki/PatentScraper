package scraper.core.processors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import scraper.core.*;

import java.io.IOException;

public class OnlinePageProcessor extends PropertyProcessor {

	private static final String PROPERTY_MESSAGE = "Downloading page";

	private org.jsoup.nodes.Document document;

	public OnlinePageProcessor() {
		super(PROPERTY_MESSAGE);
	}

	@Override
	public void processDocument(Document document) {
		tryRetrievePage(document.pageLink);
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
