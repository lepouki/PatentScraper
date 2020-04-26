package scraper.application.widgets;

import java.nio.file.*;

public class OutputDirectoryFileChooserListener extends FileChooserListener {

	public OutputDirectoryFileChooserListener(FileMode fileMode, String dialogTitle) {
		super(fileMode, dialogTitle);
	}

	@Override
	public void onFileChooserNotifierUpdated(String fileChooserText) {
		Path chosenFilePath = Paths.get(fileChooserText);
		String defaultDirectoryPath = chosenFilePath.getParent().toString();
		setFilePathText(defaultDirectoryPath);
	}

}
