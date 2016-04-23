# Connect Four Kata

[![Build Status](https://travis-ci.org/michaelruocco/connect-four-kata.svg?branch=master)](https://travis-ci.org/michaelruocco/connect-four-kata)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/connect-four-kata/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/connect-four-kata?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/571b82c6fcd19a00518565e8/badge.svg?style=flat)](https://www.versioneye.com/user/projects/571b82c6fcd19a00518565e8)

This project is my attempt at the monopoly kata exercise that is outlined here:

http://agilekatas.co.uk/katas/ConnectFour-Kata

The implementation has been developed using TDD and I have tried to keep the code as clean and simple as possible. Feedback and comments are very welcome!

## Running the Tests

You can run the unit tests for this project by running:

```
gradlew clean build
```

## Playing the Game

To play the game you will need to build the fatJar using gradle by running:

```
gradlew clean build fatJar
```

The you can navigate into the build/libs directory and run the jar by running:

```
java -jar connect-four-kata-all-1.0.0-SNAPSHOT.jar
```