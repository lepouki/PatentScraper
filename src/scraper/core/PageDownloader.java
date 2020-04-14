package scraper.core;

import java.io.IOException;
import java.net.URL;

public class PageDownloader {

	public static String download(String pageLink) throws IOException {
		return new String(
			retrievePageContent(pageLink)
		);
	}

	private static byte[] retrievePageContent(String pageLink) throws IOException {
		return new URL(pageLink).openStream().readAllBytes();
	}

}
