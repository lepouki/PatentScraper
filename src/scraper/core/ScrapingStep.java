package scraper.core;

public class ScrapingStep {

	private Target target;
	private String description;

	public ScrapingStep(Target target, String description) {
		setTarget(target);
		this.description = description;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public String getDescription() {
		return description;
	}

	public void writeStepInformation(Document document) {
		String stepInformation = scrapeDocumentStepInformation(document);
		target.write(stepInformation);
	}

	/**
	 * Gets overridden by the actual scraping steps.
	 */
	protected String scrapeDocumentStepInformation(Document document) {
		return "";
	}

}
