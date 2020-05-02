package scraper.core.properties;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.application.ScraperPaths;
import scraper.core.*;
import scraper.core.writers.CsvFileWriter;

import java.util.Arrays;

public class ClassificationsScraper extends CsvConvertiblePagePropertyScraper {

	private static final String READABLE_NAME = "Classifications";

	public ClassificationsScraper(PageScraper pageScraper) {
		super(READABLE_NAME, pageScraper);
		createFileWriter();
	}

	private void createFileWriter() {
		CsvFileWriter fileWriter = new CsvFileWriter();
		setFileWriter(fileWriter);
		setFileWriterColumnNames(fileWriter);
	}

	private void setFileWriterColumnNames(CsvFileWriter fileWriter) {
		String[] columnNames = getColumnNames();

		fileWriter.setColumnNames(
			Arrays.asList(columnNames)
		);
	}

	private static String[] getColumnNames() {
		return new String[] {"Code", "Description"};
	}

	@Override
	protected void processProperties(Document document) throws NoSuchPropertyException {
		Elements classificationElements = select("li[itemprop=cpcs]");

		for (Element classificationElement : classificationElements) {
			processClassification(classificationElement);
		}

		setOutputFileForDocument(document);
	}

	private void processClassification(Element classificationElement) {
		String code = getClassificationCode(classificationElement);
		String description = getClassificationDescription(classificationElement);

		pushClassification(
			new Classification(code, description)
		);
	}

	private void pushClassification(Classification classification) {
		boolean isUnique = !containsProperty(classification);

		if (isUnique) {
			pushProperty(classification);
		}
	}

	private String getClassificationCode(Element classificationElement) {
		String selector = getClassificationCodeSelector();
		return classificationElement.selectFirst(selector).ownText();
	}

	private String getClassificationDescription(Element classificationElement) {
		Element descriptionElement = classificationElement.selectFirst("span[itemprop=Description]");

		if (descriptionElement == null) {
			return "";
		}

		return descriptionElement.ownText();
	}

	public static String getClassificationCodeSelector() {
		return "span[itemprop=Code]";
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile(ScraperPaths.CLASSIFICATIONS_DIRECTORY + document.identifier + ".csv");
	}

}
