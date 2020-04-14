package scraper.core.processors;

public abstract class SinglePropertyPageProcessor extends PagePropertyProcessor {

	private final String propertyName;

	public SinglePropertyPageProcessor(String propertyName, PageProcessor pageProcessor) {
		super(pageProcessor);
		this.propertyName = propertyName;
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] {propertyName};
	}

	@Override
	public String[] getPropertyData() {
		String propertyValue = getPropertyValue();
		return new String[] {propertyValue};
	}

	protected abstract String getPropertyValue();

}
