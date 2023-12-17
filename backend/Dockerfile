# Build stage
FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build the project
RUN gradle build --no-daemon --warning-mode all -x test

# List contents of the build output directory
RUN ls -la /home/gradle/src/build/libs/

LABEL org.name="Andy Thieu"
# Package stage
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /home/gradle/src/build/libs/WebTech-Chat-Application-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

