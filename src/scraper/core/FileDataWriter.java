package scraper.core;

import java.io.IOException;

public interface FileDataWriter {

	void write(String data) throws IOException;

	void setFile(String filePath) throws IOException;

	void close() throws IOException;

}
