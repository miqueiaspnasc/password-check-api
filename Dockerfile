FROM openjdk:11-jdk-slim
WORKDIR /app
EXPOSE 7000
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]