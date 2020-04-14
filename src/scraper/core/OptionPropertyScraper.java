package scraper.core;

public class OptionPropertyScraper extends PropertyScraper {

	private final String optionName;

	public OptionPropertyScraper(String optionName, PropertyProcessor propertyProcessor) {
		super(propertyProcessor);
		this.optionName = optionName;
	}

	public String getOptionName() {
		return optionName;
	}

}
