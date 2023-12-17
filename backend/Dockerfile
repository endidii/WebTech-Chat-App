# Use a base image with Java (specify the version you need)
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the gradle wrapper and other gradle files
COPY backend/gradlew backend/gradlew.bat backend/gradle /app/

# Copy the build file
COPY backend/build.gradle backend/settings.gradle /app/

# Copy the source code
COPY backend/src /app/src

# Grant execution permissions to the gradlew script
RUN chmod +x /app/gradlew

# Build the application
RUN ./gradlew build

# Run the application
CMD ["java", "-jar", "build/libs/WebTech-Chat-App-0.0.1-SNAPSHOT.jar"]
