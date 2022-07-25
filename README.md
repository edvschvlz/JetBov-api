# JetBov-API

- [Technologies](#technologies)
- [Requirements](#requirements)
- [Installation](#installation)
- [You can access](#you-can-access)

## Technologies

- Spring Boot
- Swagger

## Requirements

- Java >= 17
- Maven 3.8.5

## Installation

1. Clone the repository

    ```sh
    git clone https://github.com/edvschvlz/JetBov-api.git
    ```

2. Install dependencies

    ```sh
    mvn clean package
    ```

3. Run the application

    ```sh
    mvn spring-boot:run
    ```

4. The application will be exposed on port `8080`

## Run using docker

1. Building docker image
   `docker build -t jetbov-api:1 .`

2. Running the image
   `docker run -p 8080:8080 jetbov-api:1 -d`

3. The application will be exposed on port `8080`

## API Reference

- [Swagger](http://localhost:8080/swagger-ui/index.html) (Project must be running)
- [Postman Collection](./JetBov-API.postman_collection)