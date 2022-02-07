# REST ASSURED BASIC PROJECT

This is an example of APi testing  using REST ASSURED 

### Tools used to create this project:

-  JAVA
-  MAVEN
-  TESTNG
-  REST ASSURED

## Description of the Packages in this project

This project is divided in these packages: src.test and  src.main. And the test package contains util and resources folders.

### src.test:

This package contains all the test  written using the Gherkin language and TESTNG annotations.

### src.test.util:

This is an important folder. Because it contains all feature files (with .feature extension), this is where the tests are written using the Gherkin language. And all tests should sit inside it, because, by default, Cypress will only looks for test files here.

### src.test.resources:

Helps modify or extend the internal behavior of Cypress. Users can extend this framework or customize it beyond what Cypress offers by default.


### Other files:

-   log4j.properties:The log4j.properties file is a log4j configuration file which stores properties in key-value pairs. The log4j properties file contains the entire runtime configuration used by log4j. This file will contain log4j appenders information, log level information and output file names for file appenders.
-   TestNG.xml: is a configuration file that helps in organizing our tests. It allows testers to create and handle multiple test classes, define test suites and tests.
## How to use this project?


# Pre-requisites📋
1. Java v1.8 update 151 or higher And JDK (Environment variables configurated)
2. MAven v3.8.4 or latest

# Install 🔧
- Import the project files Intelijj or Eclipse IDE el proyecto desde Eclipse o Intellij IDE 

# Compile the project 🔨
- To compile the project should execute the command: mvn package -Dmaven.test.skip=true	

# To execute the tests ⚙️

### Exectucion commands 💻

- To run all the tests of the project locally using maven, type the command: `mvn clean verify`
- execute specific test: `mvn clean test -Dtest="nameTestFile"`

note: we use clean to Clears the target directory into which Maven normally builds your project.

