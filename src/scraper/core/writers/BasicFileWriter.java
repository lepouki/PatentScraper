package scraper.core.writers;

import scraper.core.FileWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicFileWriter implements FileWriter {

	private FileOutputStream fileOutputStream;

	@Override
	public void write(String data) {
		byte[] dataBytes = data.getBytes();
		tryWriteToOutputFile(dataBytes);
	}

	@Override
	public void openFile(String filePath) {
		checkFile(filePath);

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

	private void tryWriteToOutputFile(byte[] bytes) {
		try {
			fileOutputStream.write(bytes);
		}
		catch (IOException ignored) {}
	}

}
