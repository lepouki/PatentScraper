package scraper.core;

public class HashCodeComparable {

	@Override
	public boolean equals(Object other) {
		return hashCode() == other.hashCode();
	}

}
