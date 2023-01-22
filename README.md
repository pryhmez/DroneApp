
# Musala soft test by Isaac Edmund

Spring boot application built using H2 in-memory database to for management of medication delivery using drones.


## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
## Prerequisites

Java 8 or higher
\
Maven 3.x



## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Build the application using Maven:

```bash
  cd my-project
  mvn clean install
```

Run the application:

```bash
  mvn spring-boot:run
```

Usage

```bash
Once the application is running, you can access the documentation by navigating to http://localhost:8080/swagger-ui/index.html#/ in your web browser
```
Deployment:

To build a jar file that is ready for deployment:
```
mvn clean install -Pprod
```

##Note that
The that the in-memory database (H2) has already been setup and saves to the folder data which has already been preloaded with mock data of some Drones and Mdications therefore no configuration or connection to a database is needed.



## Documentation

Visit
http://localhost:8080/swagger-ui/index.html#/
to view the swagger UI Documentation.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2 database](https://maven.apache.org/) - Inmemory data used
