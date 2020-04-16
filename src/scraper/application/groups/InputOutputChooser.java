package scraper.application.groups;

import scraper.application.*;
import scraper.application.parsers.*;
import scraper.application.widgets.FileChooser;

import java.awt.*;
import javax.swing.*;

public class InputOutputChooser extends WidgetGroup {

	private static final String TITLE = "Input and output";
	private static final String CUSTOM_INPUT_CSV_OPTION_TEXT = "Ad hoc CSV";
	private static final String INPUT_FILE_DIALOG_TEXT = "Input file";
	private static final String OUTPUT_DIRECTORY_DIALOG_TEXT = "Output directory";

	private FileChooser inputFileChooser, outputDirectoryChooser;
	private JCheckBox customInputCsvOption;

	public InputOutputChooser() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createFileChoosers();
		createCustomInputCsvOption();
	}

	private void createFileChoosers() {
		inputFileChooser = new FileChooser(FileChooser.FileMode.FILES, INPUT_FILE_DIALOG_TEXT);
		add(inputFileChooser);

		createComponentSeparator();

		outputDirectoryChooser = new FileChooser(FileChooser.FileMode.DIRECTORIES, OUTPUT_DIRECTORY_DIALOG_TEXT);
		add(outputDirectoryChooser);
	}

	private void createCustomInputCsvOption() {
		createComponentSeparator();

		customInputCsvOption = new JCheckBox(CUSTOM_INPUT_CSV_OPTION_TEXT);
		add(customInputCsvOption);
		customInputCsvOption.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public String getInputFilePathText() {
		return inputFileChooser.getFilePathText().strip();
	}

	public String getOutputDirectoryPathText() {
		return outputDirectoryChooser.getFilePathText().strip();
	}

	public CsvParser getCsvParser() {
		boolean customCsv = customInputCsvOption.isSelected();

		if (customCsv) {
			return new CustomCsvParser();
		}

		return new GooglePatentsCsvParser();
	}

}
