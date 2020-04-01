package scraper.core;

public class ScrapingStep {

	private WriteTarget writeTarget;
	private String description;

	public ScrapingStep(WriteTarget writeTarget, String description) {
		setWriteTarget(writeTarget);
		this.description = description;
	}

	public void setWriteTarget(WriteTarget writeTarget) {
		this.writeTarget = writeTarget;
	}

	public String getDescription() {
		return description;
	}

	public void writeStepInformation(Document document) {
		String stepInformation = retrieveStepInformation(document);
		writeTarget.write(stepInformation);
	}

	/**
	 * Gets overridden by the actual scraping steps.
	 */
	protected String retrieveStepInformation(Document document) {
		return "";
	}

}
