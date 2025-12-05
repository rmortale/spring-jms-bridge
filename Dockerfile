FROM eclipse-temurin:21-jre-ubi9-minimal

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
