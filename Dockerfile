FROM openjdk:17-jdk-slim

WORKDIR /app
COPY ./target/searchhistory-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "searchhistory-0.0.1-SNAPSHOT.jar"]