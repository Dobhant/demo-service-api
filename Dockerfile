FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /opt/app
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]