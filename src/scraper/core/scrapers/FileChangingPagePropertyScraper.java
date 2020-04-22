package scraper.core.scrapers;

import java.io.File;

public class FileChangingPagePropertyScraper extends PagePropertyScraper {

	private String rootDirectory;

	public FileChangingPagePropertyScraper(String readableName, PageScraper pageScraper) {
		super(readableName, pageScraper);
	}

	@Override
	public void initialize(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	@Override
	public void setFileWriterFile(String relativeFilePath) {
		super.setFileWriterFile(rootDirectory + File.separator + relativeFilePath);
	}

}
