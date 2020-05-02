package scraper.core.properties;

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
	public void cleanupResources() {
		closeFileWriter();
	}

	protected void setRelativeFileWriterFile(String relativeFilePath) {
		closeFileWriter();
		setFileWriterFile(rootDirectory + File.separator + relativeFilePath);
	}

}
