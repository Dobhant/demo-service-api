# Базовый образ
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/notificationservice-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]