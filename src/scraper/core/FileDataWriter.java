package scraper.core;

public interface FileDataWriter {

	void write(String data);

	void setFile(String filePath);

	void close();

}
