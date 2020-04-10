package scraper.core.writers;

import scraper.core.DataWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDataWriter implements DataWriter {

	private final FileOutputStream fileOutputStream;

	public FileDataWriter(String filePath) throws FileNotFoundException {
		fileOutputStream = new FileOutputStream(filePath);
	}

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

}
