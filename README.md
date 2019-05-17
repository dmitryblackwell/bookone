# SpringProjects
__Awesome things are in development...__
__version: 1.1.0__

##Getting started

### Basic setup

1. Clone or download bookone from github repo
    ```git
    git clone https://github.com/dmitryblackwell/bookone.git
    ```
1. Make sure your JDK is downloaded and JAVA_HOME is setup correct.
1. Setup DB connection and properties. You can find all scripts in db-create folder.

### Import and run project from IDE

1. Firstly you need to do basic setup.
1. Open you IDE and import bookone as Maven Project.
1. Wait until project is resolved and all dependencies are set.
1. Run BookoneApplication class as java application.
1. Enjoy.

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


### Development history

- 1.0.0 - Basic working project.
- 1.1.0 - Move project to spring boot. Add lombok.