FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml ./

RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre

RUN groupadd -r yalotengo && useradd -r -g yalotengo -d /app -s /bin/bash yalotengo

WORKDIR /app

