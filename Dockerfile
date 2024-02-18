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


# Gunakan gambar node.js versi 18
FROM node:18

# Instal Railway CLI secara global
RUN npm install -g @railway/cli

# Direktori kerja di dalam kontainer
WORKDIR /app

# Copy file package.json dan package-lock.json jika ada dan instal dependencies
RUN npm install

# Copy seluruh isi direktori ke dalam kontainer
COPY . .

# Jalankan perintah login Railway dan perintah deploy saat kontainer dijalankan
CMD ["railway", "login", "--apiKey", "$RAILWAY_API_KEY", "&&", "railway", "up", "--service", "silk", "--project", "$RAILWAY_PROJECT"]
