package scraper.core.properties;

import org.json.*;
import scraper.core.*;

import java.io.IOException;

public class GenderWithProbabilityScraper extends PagePropertyScraper {

	private static final String READABLE_NAME = "Gender with probability";
	private static final String GENDER_REQUEST_PAGE_LINK_PREFIX = "https://api.genderize.io/?name=";

	private String gender;
	private double genderProbability;

	public GenderWithProbabilityScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"Gender", "Gender probability"};
	}

	@Override
	public void processDocument(Document document) throws NoSuchPropertyException {
		String inventorOrAuthor = getInventorOrAuthor();
		String genderRequestPageLink = makeGenderRequestPageLink(inventorOrAuthor);
		processGenderRequest(genderRequestPageLink);
	}

	private String getInventorOrAuthor() throws NoSuchPropertyException {
		String selector = InventorOrAuthorScraper.getInventorSelector();
		return selectFirst(selector).ownText();
	}

	private String makeGenderRequestPageLink(String inventorOrAuthor) {
		return GENDER_REQUEST_PAGE_LINK_PREFIX + getFirstName(inventorOrAuthor);
	}

	private String getFirstName(String name) {
		final String nameSeparator = Character.toString(' ');
		return name.split(nameSeparator)[0];
	}

	private void processGenderRequest(String pageLink) throws NoSuchPropertyException {
		String requestResult = tryRetrieveGenderData(pageLink);
		JSONObject requestResultJson = tryParseGenderRequestResult(requestResult);
		tryProcessRequestResultJson(requestResultJson);
	}

	private String tryRetrieveGenderData(String pageLink) {
		try {
			return PageDownloader.retrieveText(pageLink);
		}
		catch (IOException exception) {
			return "";
		}
	}

	private JSONObject tryParseGenderRequestResult(String genderRequestResult) throws NoSuchPropertyException {
		try {
			return new JSONObject(genderRequestResult);
		}
		catch (JSONException exception) {
			throw new NoSuchPropertyException();
		}
	}

	private void tryProcessRequestResultJson(JSONObject requestResultJson) throws NoSuchPropertyException {
		try {
			gender = requestResultJson.getString("gender");
			genderProbability = requestResultJson.getDouble("probability");
		}
		catch (JSONException exception) {
			throw new NoSuchPropertyException();
		}
	}

	@Override
	public String[] getPropertyData() {
		String genderProbabilityString = Double.toString(genderProbability);
		return new String[] {gender, genderProbabilityString};
	}

}
