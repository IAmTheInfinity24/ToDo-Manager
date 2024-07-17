

# Start with a base image that includes JDK and Maven
FROM maven:3.8.9-jdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .

# Resolve Maven dependencies (download dependencies only)
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Copy the executable JAR file from the host to the container
COPY target/taskManager-0.0.1-SNAPSHOT.jar /usr/app/

# Set the working directory in the container
WORKDIR /usr/app/

# For Gradle, it would be something like:
# COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "taskManager-0.0.1-SNAPSHOT.jar"]






