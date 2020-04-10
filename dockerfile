FROM openjdk:8-jdk-alpine
ADD target/springboot-rest-swagger.jar testapp.jar
EXPOSE 8080
ENTRYPOINT java -jar testapp.jar