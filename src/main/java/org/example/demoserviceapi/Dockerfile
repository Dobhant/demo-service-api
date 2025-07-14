# Используем базовый образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем jar-файл (имя jar-файла может быть другое — проверь!)
COPY target/demoserviceapi-1.0.0.jar app.jar

# Открываем порт (Spring Boot по умолчанию использует 8080)
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]