package scraper.core;

public class ConcatenationOptions {

	public boolean concatenateAbstract;
	public boolean concatenateDescription;
	public boolean concatenateClaims;

	public ConcatenationOptions(
		boolean concatenateAbstract, boolean concatenateDescription, boolean concatenateClaims)
	{
		this.concatenateAbstract = concatenateAbstract;
		this.concatenateDescription = concatenateDescription;
		this.concatenateClaims = concatenateClaims;
	}

	public boolean nothingSelected() {
		return !concatenateAbstract && !concatenateDescription && !concatenateClaims;
	}

}
