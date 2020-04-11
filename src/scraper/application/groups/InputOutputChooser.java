package scraper.application.groups;

import scraper.application.*;
import scraper.application.parsers.*;
import scraper.application.widgets.FileChooser;

import java.awt.*;
import javax.swing.*;

public class InputOutputChooser extends WidgetGroup {

	private static final String TITLE = "Input and output";
	private static final String CUSTOM_INPUT_CSV_OPTION_TEXT = "Custom input CSV";

	private FileChooser inputFileChooser, outputDirectoryChooser;
	private JCheckBox customInputCsvOption;

	public InputOutputChooser() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createFileChoosers();
		createCustomCsvOption();
	}

	private void createFileChoosers() {
		inputFileChooser = new FileChooser(FileChooser.FileMode.FILES);
		add(inputFileChooser);

		createSeparator();

		outputDirectoryChooser = new FileChooser(FileChooser.FileMode.DIRECTORIES);
		add(outputDirectoryChooser);
	}

	private void createSeparator() {
		Component separator = Box.createRigidArea(
			new Dimension(0, LayoutConfiguration.PADDING)
		);

		add(separator);
	}

	private void createCustomCsvOption() {
		createSeparator();

		customInputCsvOption = new JCheckBox(CUSTOM_INPUT_CSV_OPTION_TEXT);
		add(customInputCsvOption);
		customInputCsvOption.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public String getInputFilePathText() {
		return inputFileChooser.getFilePathText();
	}

	public String getOutputDirectoryPathText() {
		return outputDirectoryChooser.getFilePathText();
	}

	public CsvParser getCsvParser() {
		boolean customCsv = customInputCsvOption.isSelected();

		if (customCsv) {
			return new CustomCsvParser();
		}

		return new GooglePatentsCsvParser();
	}

}
