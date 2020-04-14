package scraper.core.writers;

import scraper.core.FileDataWriter;

import java.io.*;
import java.nio.file.*;

public class BasicFileDataWriter implements FileDataWriter {

	private static class FileWritingException extends IOException {

		public FileWritingException(String filePath) {
			super("Failed to write to file \"" + filePath + "\"");
		}

	}

	private static class FileOpeningException extends IOException {

		public FileOpeningException(String filePath) {
			super("Failed to open file \"" + filePath + "\"");
		}

	}

	private static class FileClosingException extends IOException {

		public FileClosingException(String filePath) {
			super("Failed to close file \"" + filePath + "\"");
		}

	}

	private String filePath;
	private FileOutputStream fileOutputStream;

	@Override
	public void write(String data) throws IOException {
		byte[] dataBytes = data.getBytes();
		tryWriteToFile(dataBytes);
	}

	private void tryWriteToFile(byte[] data) throws FileWritingException {
		try {
			fileOutputStream.write(data);
		}
		catch (IOException exception) {
			throw new FileWritingException(filePath);
		}
	}

	@Override
	public void setFile(String filePath) throws IOException {
		createFileIfDoesNotExists(filePath);
		tryOpenFile(filePath);
	}

	private void tryOpenFile(String filePath) throws FileOpeningException {
		try {
			fileOutputStream = new FileOutputStream(filePath);
		}
		catch (IOException exception) {
			throw new FileOpeningException(filePath);
		}

		this.filePath = filePath;
	}

	private void createFileIfDoesNotExists(String filePath) {
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

	@Override
	public void close() throws IOException {
		try {
			fileOutputStream.close();
		}
		catch (IOException exception) {
			throw new FileClosingException(filePath);
		}
	}

}
