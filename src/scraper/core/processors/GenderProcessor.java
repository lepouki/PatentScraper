package scraper.core.processors;

import scraper.core.Document;
import scraper.core.PageDownloader;

import java.io.IOException;

public class GenderProcessor extends PagePropertyProcessor {

	private static final String PROPERTY_NAME = "Gender";
	private static final String GENDER_API_URL_PREFIX = "https://api.genderize.io/?name=";

	private String gender;
	private double genderProbability = 0.0;

	public GenderProcessor(PageProcessor pageProcessor) {
		super(PROPERTY_NAME, pageProcessor);
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String inventorOrAuthor = getInventorOrAuthor();
		String genderRequestPageLink = GENDER_API_URL_PREFIX + getFirstName(inventorOrAuthor);
		processGenderRequest(genderRequestPageLink);
	}

	private String getInventorOrAuthor() throws NoSuchPropertyException {
		String selector = InventorOrAuthorProcessor.getSelector();
		return selectFirst(selector).ownText();
	}

	private String getFirstName(String name) {
		final String nameSeparator = Character.toString(' ');
		return name.split(nameSeparator)[0];
	}

	private void processGenderRequest(String pageLink) {
		String genderRequestResult = tryRetrieveGenderRequest(pageLink);
		gender = "";
	}

	private String tryRetrieveGenderRequest(String pageLink) {
		try {
			return PageDownloader.download(pageLink);
		}
		catch (IOException ignored) {}

		return "";
	}

	@Override
	public String getPropertyData() {
		return gender;
	}

	public double getGenderProbability() {
		return genderProbability;
	}

}
