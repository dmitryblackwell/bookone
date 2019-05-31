# BookOne

##### Master Status: 
[![Maintainability](https://api.codeclimate.com/v1/badges/e908eabd23e03aa282aa/maintainability)](https://codeclimate.com/github/dmitryblackwell/bookone/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e908eabd23e03aa282aa/test_coverage)](https://codeclimate.com/github/dmitryblackwell/bookone/test_coverage)
[![Build Status](https://travis-ci.org/dmitryblackwell/bookone.svg?branch=master)](https://travis-ci.org/dmitryblackwell/bookone)

##### version: __1.3.1__

## Getting started

### Basic setup

1. Clone or download bookone from github repo
    ```git
    git clone https://github.com/dmitryblackwell/bookone.git
    ```
1. Make sure your JDK is downloaded and JAVA_HOME is setup correct.
1. Setup [DB connection](#db-connection) or [Mocks](#mocks).

#### <a name="mocks"></a> Setup Mocks
 
To enable mocks follow next steps:

1. Go to `/src/main/resources/application.properties`
1. Set `mocks.enabled=true`
1. Make sure next line is present and not comment:
    ```
    spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
    ```
    This one is turning of datasource autoconfig. Don't forget to delete it, if you are using database!

#### <a name="db-connection"></a> Setup DataBase Connection
1. Set up database using scripts in `db-scripts`
1. Set up database properties (url, user, password) in `/src/main/resources/persistence-mysql.properties`
1. Go to `/src/main/resources/application.properties`
1. Set `mocks.enabled=false`
1. Make sure next line is absent or comment:
    ```
    spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
    ```

### Run project from console

To run this project without importing it to the IDE follow next steps:

1. Firstly you need to do basic setup.
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

1. Firstly you need to do basic setup.
1. Open you IDE and import bookone as Maven Project.
1. Wait until project is resolved and all dependencies are set.
1. Run BookoneApplication class as java application.
1. Enjoy.

### Run Integration tests
To test your code run next maven command:
```
clean integration-test
```
It will also create report from JaCoCo in `target/site/jacoco/index.html`

### Development history

- 1 - Basic working project.
    - 1.1 - Move project to spring boot. Add lombok.
    - 1.2 - Add mocks and Travis CI.
    - 1.3 - Integration tests.
        - 1.3.1 - Add more tests.