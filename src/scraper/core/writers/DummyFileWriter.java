package scraper.core.writers;

import scraper.core.FileWriter;

public class DummyFileWriter implements FileWriter {

	@Override
	public void write(String[] entries) {
	}

	@Override
	public void write(String entry) {
	}

	@Override
	public void writeBytes(byte[] bytes) {
	}

	@Override
	public void setFile(String filePath) {
	}

	@Override
	public void close() {
	}

}
