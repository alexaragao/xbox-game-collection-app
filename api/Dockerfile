# Use an official OpenJDK 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-focal

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and the build.gradle and settings.gradle files
COPY gradlew gradlew
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY gradle gradle

# Copy the rest of the project files
COPY src src

# Grant execute permission to the Gradle wrapper
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Copy the built jar file to the working directory
COPY build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 5000

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
