package scraper.core;

public class HashCodeComparable {

	@Override
	public boolean equals(Object other) {
		return (other instanceof HashCodeComparable) && hashCode() == other.hashCode();
	}

}
