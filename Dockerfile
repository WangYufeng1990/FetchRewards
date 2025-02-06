
FROM amazoncorretto:8-alpine-jdk

WORKDIR /app

COPY target/receiptProcessor-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "receiptProcessor-0.0.1-SNAPSHOT.jar"]
