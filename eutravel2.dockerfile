FROM openjdk:11
EXPOSE 8084
WORKDIR /app
COPY target/eutravelcenter2-0.0.5-SNAPSHOT.jar target/app.jar

ENTRYPOINT ["java","-jar","target/app.jar"]