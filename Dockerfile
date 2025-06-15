# Stage 1: Build usando Maven e JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG CHAVE_API_IA

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV CHAVE_API_IA=${CHAVE_API_IA}

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Runtime com OpenJDK 21
FROM eclipse-temurin:21-jdk

WORKDIR /app

EXPOSE 8080

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV CHAVE_API_IA=${CHAVE_API_IA}

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
