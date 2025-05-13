FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/tech_spec_java_spring_final-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]