package scraper.core;

public class Event extends CsvConvertible {

	public String code;
	public String title;
	public String date;
	public String description;

	public Event(
		String code, String title, String date, String description)
	{
		this.code = code;
		this.title = title;
		this.date = date;
		this.description = description;
	}

	@Override
	public String[] toCsvEntries() {
		return new String[] {date, code, title, description};
	}

}
