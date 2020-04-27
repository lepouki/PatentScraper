package scraper.application.widgets;

import java.util.*;

public class FileChooserNotifier extends FileChooser {

	private final Set<FileChooserListener> listeners;

	public FileChooserNotifier(FileMode fileMode, String dialogTitle) {
		super(fileMode, dialogTitle);
		listeners = new HashSet<>();
	}

	public void pushFileChooserListener(FileChooserListener fileChooser) {
		listeners.add(fileChooser);
	}

	@Override
	protected void updateFilePathText() {
		super.updateFilePathText();
		notifyListeners();
	}

	public void notifyListeners() {
		String text = getFilePathText();

		for (FileChooserListener listener : listeners) {
			listener.onFileChooserNotifierUpdated(text);
		}
	}

}
