# PatentScraper
A web scraper targeting the Google Patents database.

Made during an internship.

# Building

Using [libgdx/packr](https://github.com/libgdx/packr):
```
java -jar <packr-jar-name> \
	--platform <platform> \
	--jdk <path-to-jdk-11-or-more> \
	--useZgcIfSupportedOs \
	--executable patent-scraper \
	--classpath <path-to-scraper-jar> \
	--removelibs <path-to-scraper-jar> \
	--mainclass scraper.Main \
	--minimizejre soft \
	--output <app-name>
```
