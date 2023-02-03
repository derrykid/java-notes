FROM openjdk:17-alpine

COPY target/*.jar app.jar

COPY *.json /

ENTRYPOINT ["java", "-jar", "app.jar"]
