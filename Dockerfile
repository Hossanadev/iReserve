FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package -DskipTests

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /app/target/iReserve-0.0.1-SNAPSHOT.jar /app/ireserve.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/ireserve.jar"]
