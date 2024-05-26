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






