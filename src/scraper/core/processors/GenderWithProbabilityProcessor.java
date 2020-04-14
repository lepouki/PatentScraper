package scraper.core.processors;

import org.json.JSONException;
import org.json.JSONObject;
import scraper.core.Document;
import scraper.core.PageDownloader;

import java.io.IOException;

public class GenderWithProbabilityProcessor extends PagePropertyProcessor {

	private static final String GENDER_REQUEST_PAGE_LINK_PREFIX = "https://api.genderize.io/?name=";

	private String gender;
	private double genderProbability;

	public GenderWithProbabilityProcessor(PageProcessor pageProcessor) {
		super(pageProcessor);
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {"gender", "gender probability"};
	}

	@Override
	public void processDocument(Document document) {
		tryRequestGender();
	}

	private void tryRequestGender() {
		try {
			String inventorOrAuthor = getInventorOrAuthor();
			String genderRequestPageLink = makeGenderRequestPageLink(inventorOrAuthor);
			processGenderRequest(genderRequestPageLink);
		}
		catch (NoSuchPropertyException exception) {
			gender = "unknown";
			genderProbability = 0.0f;
		}
	}

	private String getInventorOrAuthor() throws NoSuchPropertyException {
		String selector = InventorOrAuthorProcessor.getSelector();
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
			return PageDownloader.download(pageLink);
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
		catch (JSONException ignored) {
			throw new NoSuchPropertyException();
		}
	}

	@Override
	public String[] getPropertyData() {
		String genderProbabilityString = Double.toString(genderProbability);
		return new String[] {gender, genderProbabilityString};
	}

}
