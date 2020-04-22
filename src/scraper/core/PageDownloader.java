package scraper.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;

public class PageDownloader {

	public static Document retrieveDocument(String pageLink) throws IOException {
		return Jsoup.connect(pageLink).get();
	}

	public static String retrieveText(String pageLink) throws IOException {
		return new String(
			retrievePageContent(pageLink)
		);
	}

	public static String retrieveBinary(String pageLink) throws IOException {
		return Base64.getEncoder().encodeToString(
			retrievePageContent(pageLink)
		);
	}

	private static byte[] retrievePageContent(String pageLink) throws IOException {
		return new URL(pageLink).openStream().readAllBytes();
	}

}
