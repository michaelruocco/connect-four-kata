# Connect Four Kata

[![Build Status](https://travis-ci.org/michaelruocco/connect-four-kata.svg?branch=master)](https://travis-ci.org/michaelruocco/connect-four-kata)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/connect-four-kata/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/connect-four-kata?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/adef6c908cd1464d8374d9ade10736d5)](https://www.codacy.com/app/michael-ruocco/connect-four-kata?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/connect-four-kata&amp;utm_campaign=Badge_Grade)

This project is my attempt at the connect four kata exercise that is outlined here:

http://agilekatas.co.uk/katas/ConnectFour-Kata

The implementation has been developed using TDD and I have tried to keep the code as clean and simple as possible. Feedback and comments are very welcome!

## Running the tests

This project is covered by both unit tests and integration tests.

The integration tests test the UI and take slightly longer to run,
this is why they have been split out from the unit tests so each
set of tests can be run independently.

### Running the unit tests

To run just the unit tests you can run the command:

```
gradlew clean build -x integrationTest
```

### Running the integration tests

To run just the integration tests you can run the command:

```
gradlew clean build -x test
```

### Running all the tests

Finally to run all the integration tests you can run the command:

```
gradlew clean build
```

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```

## Playing the Game

To play the game you will need to build the fatJar using gradle by running:

```
gradlew clean build allJar
```

The game can be played in two modes, with a gui interface or using the console.
Navigate into the build/libs directory and run the jar by running:

```
java -jar connect-four-kata-all-1.0.0-SNAPSHOT.jar
```

this, will run the game in gui made by default, to play the game in
console mode you can run:

```
java -jar connect-four-kata-all-1.0.0-SNAPSHOT.jar console
```
