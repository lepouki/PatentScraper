package scraper.application.widgets;

public abstract class FileChooserListener extends FileChooser {

	public FileChooserListener(FileMode fileMode, String dialogTitle) {
		super(fileMode, dialogTitle);
	}

	public abstract void onFileChooserNotifierUpdated(String fileChooserText);

}
