package scraper.core.writers;

import scraper.core.FileDataWriter;

import java.io.*;
import java.nio.file.*;

public class BasicFileDataWriter implements FileDataWriter {

	private String currentFilePath = "";
	private FileOutputStream fileOutputStream;

	@Override
	public void write(String data) {
		byte[] dataBytes = data.getBytes();
		tryWriteToOutputFile(dataBytes);
	}

	private void tryWriteToOutputFile(byte[] bytes) {
		try {
			fileOutputStream.write(bytes);
		}
		catch (IOException ignored) {}
	}

	@Override
	public void setFile(String filePath) {
		boolean sameAsCurrentFile = filePath.equals(currentFilePath);
		if (sameAsCurrentFile) return;

		checkFile(filePath);
		tryCreateFileOutputStream(filePath);
		currentFilePath = filePath;
	}

	private void checkFile(String filePath) {
		Path path = Paths.get(filePath);
		boolean exists = Files.exists(path);

		if (!exists) {
			createFile(path);
		}
	}

	private void createFile(Path file) {
		Path parentDirectory = file.getParent();
		createDirectory(parentDirectory);
		tryCreateFile(file);
	}

	private void tryCreateFile(Path file) {
		try {
			Files.createFile(file);
		}
		// The parent directory gets checked before so this should never happen
		catch (IOException ignored) {}
	}

	private void createDirectory(Path directory) {
		Path parentDirectory = directory.getParent();
		boolean parentDirectoryExists = Files.exists(parentDirectory);

		if (!parentDirectoryExists) {
			createDirectory(parentDirectory);
		}

		tryCreateDirectory(directory);
	}

	private void tryCreateDirectory(Path directory) {
		try {
			Files.createDirectory(directory);
		}
		// The parent directory gets checked before so this should never happen
		catch (IOException ignored) {}
	}

	private void tryCreateFileOutputStream(String filePath) {
		try {
			fileOutputStream = new FileOutputStream(filePath);
		}
		// The file path gets checked before so this should never happen
		catch (FileNotFoundException ignored) {}
	}

	@Override
	public void close() {
		try {
			fileOutputStream.close();
		}
		catch (IOException ignored) {}
	}

}
