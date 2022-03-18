# Java Challenge: Ten-pin bowling

This application receives a file containing the chances of a ten-pin bowling game and prints the score in the console. If the file is not found, empty or invalid in any way, an error message will be printed out instead.

### Requirements:

- Java 17
- Maven 3.8.4

### How to build:

```
mvn clean package
```
### How to run using Maven:

```
mvn exec:java -Dexec.args="PATH_TO_YOUR_FILE"
```
### How to run using the generated jar:

```
java -jar target/bowling-challenge-1.0-SNAPSHOT.jar "PATH_TO_YOUR_FILE"
```