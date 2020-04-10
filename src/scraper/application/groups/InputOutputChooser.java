package scraper.application.groups;

import scraper.application.*;
import scraper.application.widgets.FileChooser;

import java.awt.*;
import javax.swing.*;

public class InputOutputChooser extends WidgetGroup {

	private static final String TITLE = "Input and output";

	private final FileChooser inputFileChooser, outputDirectoryChooser;

	public InputOutputChooser() {
		super(TITLE, LayoutConfiguration.PADDING);

		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		inputFileChooser = new FileChooser(FileChooser.FileMode.FILES);
		add(inputFileChooser);

		createFileChooserSeparator();

		outputDirectoryChooser = new FileChooser(FileChooser.FileMode.DIRECTORIES);
		add(outputDirectoryChooser);
	}

	private void createFileChooserSeparator() {
		Component separator = Box.createRigidArea(
			new Dimension(0, LayoutConfiguration.PADDING)
		);

		add(separator);
	}

	public String getInputFilePathText() {
		return inputFileChooser.getFilePathText();
	}

	public String getOutputDirectoryPathText() {
		return outputDirectoryChooser.getFilePathText();
	}

}
