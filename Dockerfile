FROM maven:3.8.5-openjdk-17 AS build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src

RUN mvn clean package  -Dskiptests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/memory-0.0.1-SNAPSHOT.jar memory.jar

EXPOSE 9094

ENTRYPOINT [ "java","-jar","memory.jar"]
