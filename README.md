# Example project
An example project for the backend class to play around and teaching basic usage of external APIs =) 
it will creatze an html file in the project root depending in given search and filter terms

## How to build:

```shell
./gradlew build
```

> This will produce a 'fat-jar' in the build folder under `build/libs`

## How to run
(assuming you have done a build)

```shell
java -jar ./build/libs/news-cli-all.jar
```

## How to use
You can search for news either by using the [news Api](https://newsapi.org) or scraping data from rwanda news page [www.newtimes.co.rw](https://www.newtimes.co.rw) or running in a mixed mode and using both sources to search for results.
During runtime you can pass a general search term, filter titles for a keyword and decide from which source results should be fetched.

results will be stored in an `index.html` file to the current directory and can be opend in a browser of your choice.
