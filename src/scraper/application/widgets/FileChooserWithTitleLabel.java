package scraper.application.widgets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class FileChooserWithTitleLabel extends JPanel {

	private static final int PADDING_SIZE = 5;

	private FileChooser fileChooser;

	public FileChooserWithTitleLabel(String title, FileChooser.FileMode fileMode) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

		createTitleLabel(title);
		createFileChooser(fileMode);

		createPaddingBorder();
	}

	private void createTitleLabel(String title) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		add(titleLabel);
	}

	private void createFileChooser(FileChooser.FileMode fileMode) {
		fileChooser = new FileChooser(fileMode);

		add(fileChooser);
	}

	private void createPaddingBorder() {
		Border paddingBorder = new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);

		setBorder(paddingBorder);
	}

	public String getPath() {
		return fileChooser.getPath();
	}

}
