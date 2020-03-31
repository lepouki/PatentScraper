package scraper.core;

public class ScrapingStep {

	private OutputTarget outputTarget;

	public ScrapingStep(OutputTarget target) {
		setOutputTarget(target);
	}

	public void setOutputTarget(OutputTarget outputTarget) {
		this.outputTarget = outputTarget;
	}

	public void writeDocumentStepInformationToOutputTarget(Document document) {
		String stepInformation = scrapeDocumentStepInformation(document);
		outputTarget.write(stepInformation);
	}

	/**
	 * Gets overridden by the actual scraping steps.
	 */
	protected String scrapeDocumentStepInformation(Document document) {
		return "";
	}

}
