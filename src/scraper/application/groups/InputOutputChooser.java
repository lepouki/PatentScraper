package scraper.application.groups;

import scraper.application.*;
import scraper.application.parsers.*;
import scraper.application.widgets.FileChooser;
import scraper.application.widgets.FileChooserListener;
import scraper.application.widgets.FileChooserNotifier;
import scraper.application.widgets.OutputDirectoryFileChooserListener;

import java.awt.*;
import javax.swing.*;

public class InputOutputChooser extends WidgetGroup {

	private static final String TITLE = "Input and output";
	private static final String CUSTOM_INPUT_CSV_OPTION_TEXT = "Ad hoc CSV";
	private static final String INPUT_FILE_DIALOG_TITLE = "Input file";
	private static final String OUTPUT_DIRECTORY_DIALOG_TITLE = "Output directory";

	private FileChooserNotifier inputFileChooser;
	private FileChooserListener outputDirectoryChooser;
	private JCheckBox customInputCsvOption;

	public InputOutputChooser() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createFileChoosers();
		createCustomInputCsvOption();
	}

	private void createFileChoosers() {
		outputDirectoryChooser = createOutputDirectoryChooser();
		inputFileChooser = createInputFileChooser(outputDirectoryChooser);
		pushFileChoosers();
	}

	private static FileChooserListener createOutputDirectoryChooser() {
		return new OutputDirectoryFileChooserListener(
			FileChooser.FileMode.DIRECTORIES, OUTPUT_DIRECTORY_DIALOG_TITLE);
	}

	private static FileChooserNotifier createInputFileChooser(FileChooserListener outputDirectoryChooser) {
		FileChooserNotifier fileChooser = new FileChooserNotifier(FileChooser.FileMode.FILES, INPUT_FILE_DIALOG_TITLE);
		fileChooser.pushFileChooserListener(outputDirectoryChooser);
		return fileChooser;
	}

	private void pushFileChoosers() {
		add(inputFileChooser);
		createComponentSeparator();
		add(outputDirectoryChooser);
	}

	private void createCustomInputCsvOption() {
		createComponentSeparator();

		customInputCsvOption = new JCheckBox(CUSTOM_INPUT_CSV_OPTION_TEXT);
		add(customInputCsvOption);
		customInputCsvOption.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public String getInputFilePathText() {
		return getFileChooserText(inputFileChooser);
	}

	private String getFileChooserText(FileChooser fileChooser) {
		return fileChooser.getFilePathText().strip();
	}

	public String getOutputDirectoryPathText() {
		return getFileChooserText(outputDirectoryChooser);
	}

	public CsvParser getCsvParser() {
		boolean customCsv = customInputCsvOption.isSelected();

		if (customCsv) {
			return new CustomCsvParser();
		}

		return new GooglePatentsCsvParser();
	}

}
