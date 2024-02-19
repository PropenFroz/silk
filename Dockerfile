# Use the official OpenJDK 17 image as a parent image
# FROM openjdk:17-alpine

# # Set the working directory in the container
# WORKDIR /app

# # Copy the backend JAR file into the container
# COPY target/silk-1.0.jar app.jar

# # Copy the frontend build files into the container
# # COPY ui/build/ ./ui

# # Expose port 8090 for the backend application
# EXPOSE 8090

# # Command to run the backend application
# ENTRYPOINT ["java", "-jar", "app.jar"]


# # Gunakan gambar node.js versi 18
# FROM node:18

# # Instal Railway CLI secara global
# RUN npm install -g @railway/cli

# # # Direktori kerja di dalam kontainer
# # WORKDIR /app

# # Copy seluruh isi direktori ke dalam kontainer
# COPY . .

# # Jalankan perintah login Railway dan perintah deploy saat kontainer dijalankan
# # CMD ["railway", "login", "8141d166-992b-4003-96ec-1337158c1808", "&&", "railway", "up", "--service", "silk", "--project", "28697091-6c52-4df8-86ed-2c40583a30f5"]

# CMD ["railway", "up", "--service=silk -d"]


# FROM docker:latest

# RUN apk add --no-cache openjdk11
# RUN apk add --no-cache maven

# CMD ["bash"]

# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application using Maven
RUN mvn clean install

# Use a base image with Java installed
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the current directory in the container
COPY --from=build /app/target/silk-1.0.jar .

# Specify the command to run your application
CMD ["java", "-jar", "silk-1.0.jar"]
