# Fetching latest version of Java
FROM openjdk:8
 
# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/kafkawebservice-0.0.1-SNAPSHOT.jar /app

# Copy the application properties file into our app

COPY ./src/main/resources/application_docker.properties /app
# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "kafkawebservice-0.0.1-SNAPSHOT.jar", "–DApp.config.file=local.properties"]