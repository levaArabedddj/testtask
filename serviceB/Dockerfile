FROM openjdk:20-jdk-slim
WORKDIR /app
COPY target/serviceB-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
