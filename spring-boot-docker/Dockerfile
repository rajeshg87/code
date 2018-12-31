FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="rajeshkannang87@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/docker-spring-boot.jar

# Add the application's jar to the container
ADD ${JAR_FILE} docker-spring-boot.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-spring-boot.jar"]