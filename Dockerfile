FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/docker-bookstore.jar"]