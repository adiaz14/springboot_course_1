# Use an official JDK image as the base image
FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/studentgrades-0.0.1.jar
COPY ${JAR_FILE} app_students_grades.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_students_grades.jar"]