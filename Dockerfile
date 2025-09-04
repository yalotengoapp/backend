FROM maven:3.9.11-eclipse-temurin-24 AS build

WORKDIR /src

COPY . .

RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre

WORKDIR /app

COPY --from=build /src/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]