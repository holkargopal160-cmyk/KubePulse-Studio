# Use official OpenJDK runtime as parent image
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy build files / jar
COPY target/KubePulseStudio-1.0-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]