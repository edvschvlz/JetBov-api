FROM maven:3.8.5-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java17-debian11
COPY --from=build /usr/src/app/target/JetBov-api-1.0.0-SNAPSHOT.jar /usr/app/JetBov-api-1.0.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/JetBov-api-1.0.0-SNAPSHOT.jar"]