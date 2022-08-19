# JetBov-API

- [Technologies](#technologies)
- [Requirements](#requirements)
- [Installation](#installation)
- [API Reference](#api-reference)

## Technologies

- Spring Boot
- Flyway
- DBCP
- MySQL
- Swagger

## Requirements

- Java >= 17
- MySQL 8.0
- Maven 3.8.5

## Installation

1. Clone the repository

    ```sh
    git clone https://github.com/edvschvlz/JetBov-api.git
    ```

2. Set the database connection in `application.properties` 


3. Install dependencies

    ```sh
    mvn clean package
    ```

4. Run the application

    ```sh
    mvn spring-boot:run
    ```

5. The application will be exposed on port `8080`

## API Reference

- [Swagger](http://localhost:8080/swagger-ui/index.html) (Project must be running)
- [Postman Collection](./JetBov-API.postman_collection.json)