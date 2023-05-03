# Discgolf Application Backend 

This is a school team project developed using Spring Boot, Kotlin and Maven.

## Installation guide

### Prerequisites
- Java 17 or later installed on your system
- Apache Maven 3.8.4 or later installed on your system
- Git installed on your system
- A command-line interface (e.g., Terminal on macOS, Command Prompt on Windows)

### Cloning the repository
To clone the repository, run the following command:

```
git clone [repository URL]
```

### Building the application

To build the application, run the following command in the root directory of the Maven project, where the pom.xml file is located:

```
mvn clean package
```

This will compile the source code, run the tests, and package the application into a JAR file.

### Running the application

To run the application, navigate to the directory where the JAR file was generated and run the following command:

```
java -jar <jar-file-name>.jar
```

This will start the application and it will be available at http://localhost:8080.

Please see the API documentation generated by SpringDoc OpenAPI Starter WebMVC UI by navigating to http://localhost:8080/swagger-ui.html once the project is running.

Adding secrets to environment variables
To add a secret to environment variables in Kotlin, follow these steps:

Open the project in IntelliJ IDEA.

Click on "Edit Configurations" in the top right corner of the IDE.

Click on the "+" button and select "Kotlin".

In the "Environment variables" section, add the name and value of the secret. For example, to add a secret named "DATABASE_PASSWORD" with the value "mypassword", add the following line:

makefile
Copy code
DATABASE_PASSWORD=mypassword
Click "OK" to save the configuration.

Now the secret is added to the environment variables and can be accessed in the Kotlin code using the System.getenv("DATABASE_PASSWORD") method.

### About

This installation guide is generated by ChatGPT language model.

## Api documentation
Development deployment: https://dev-discgolf.herokuapp.com/swagger-ui/index.html

Production deployment: https://disc-golf-database.herokuapp.com/swagger-ui/index.html

## Deployments
Development deployment: https://dev-discgolf.herokuapp.com/

Production deployment: https://disc-golf-database.herokuapp.com/

## Mobile-App
Link to mobile-app repository is [here](https://github.com/Ohjelmistoprojekti-II-Frisbeegolf/Mobile-app)
