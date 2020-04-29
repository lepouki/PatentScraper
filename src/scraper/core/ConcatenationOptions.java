package scraper.core;

public class ConcatenationOptions {

	public boolean concatenateTitle;
	public boolean concatenateAbstract;
	public boolean concatenateDescription;
	public boolean concatenateClaims;

	public ConcatenationOptions(
		boolean concatenateTitle,
		boolean concatenateAbstract,
		boolean concatenateDescription,
		boolean concatenateClaims)
	{
		this.concatenateTitle = concatenateTitle;
		this.concatenateAbstract = concatenateAbstract;
		this.concatenateDescription = concatenateDescription;
		this.concatenateClaims = concatenateClaims;
	}

}
