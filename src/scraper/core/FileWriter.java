package scraper.core;

import java.io.IOException;

public interface FileWriter {

	void write(String[] entries) throws IOException;

	void write(String entry) throws IOException;

	void writeBytes(byte[] bytes) throws IOException;

	void setFile(String filePath) throws IOException;

	void close() throws IOException;

}
