FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/teamingBack-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
