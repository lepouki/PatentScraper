package scraper.core.scrapers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.core.Classification;
import scraper.core.Document;
import scraper.core.writers.BasicFileWriter;
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
		return new String[] {"code", "description"};
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
		String description = classificationElement.selectFirst("span[itemprop=Description]").ownText();

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

	public static String getClassificationCodeSelector() {
		return "span[itemprop=Code]";
	}

	private void setOutputFileForDocument(Document document) {
		setRelativeFileWriterFile("extra/classifications/" + document.identifier + ".csv");
	}

}
