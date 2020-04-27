package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.*;

import javax.swing.*;

public class InputOutputChooser extends WidgetGroup {

	private static final String TITLE = "Input file and output folder";
	private static final String CUSTOM_INPUT_CSV_OPTION_TEXT = "Ad hoc CSV";
	private static final String INPUT_FILE_DIALOG_TITLE = "Input file";
	private static final String OUTPUT_DIRECTORY_DIALOG_TITLE = "Output directory";

	private FileChooserNotifier inputFileChooser;
	private FileChooserListener outputDirectoryChooser;

	public InputOutputChooser() {
		super(TITLE);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createFileChoosers();
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

	public String getInputFilePathText() {
		return getFileChooserText(inputFileChooser);
	}

	private String getFileChooserText(FileChooser fileChooser) {
		return fileChooser.getFilePathText().strip();
	}

	public String getOutputDirectoryPathText() {
		return getFileChooserText(outputDirectoryChooser);
	}

}
