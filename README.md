# BookOne

##### Master Status: 
[![Maintainability](https://api.codeclimate.com/v1/badges/e908eabd23e03aa282aa/maintainability)](https://codeclimate.com/github/dmitryblackwell/bookone/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e908eabd23e03aa282aa/test_coverage)](https://codeclimate.com/github/dmitryblackwell/bookone/test_coverage)
[![Build Status](https://travis-ci.org/dmitryblackwell/bookone.svg?branch=master)](https://travis-ci.org/dmitryblackwell/bookone)

##### version: __2.0.0-SNAPSHOT__

## Getting started

### Basic setup

1. Clone or download bookone from github repo
    ```git
    git clone https://github.com/dmitryblackwell/bookone.git
    ```
1. Make sure your JDK is downloaded and JAVA_HOME is setup correct.
1. Install [MySQL WorkBench](https://dev.mysql.com/downloads/workbench/) or other RDBMS.
1. Create new connection with username add password specified in `application.properties`
1. Set up database using scripts in `db-scripts`

### Run project from console

To run this project without importing it to the IDE follow next steps:

1. Firstly, you need to do basic setup.
1. Open your command line and go to project location.
1. Enter next command for windows:
    ```git
        mvnw spring-boot:run
    ```
    or this one if you use linux:
    ```git
        ./mvnw.sh spring-boot:run
    ```
1. Wait until all dependencies are downloaded and enjoy project.


### Import and run project from IDE

1. Firstly, you need to do basic setup.
1. Open you IDE and import BookONE as Maven Project.
1. Wait until project is resolved and all dependencies are set.
1. Run BookOneApplication class as java application.
1. Enjoy.

### Run Integration tests
To test your code run next maven command:
```
clean integration-test
```
It will also create report from JaCoCo in `target/site/jacoco/index.html`

### Development history

1) Basic working project.
    - 1.1 - Move project to spring boot. Add lombok.
    - 1.2 - Add mocks and Travis CI.
    - 1.3 - Integration tests.
        - 1.3.1 - Add more tests. 
    