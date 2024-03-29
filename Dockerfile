FROM openjdk:19-slim

COPY build/libs/*.jar app.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
