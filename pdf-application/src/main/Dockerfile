FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/Users/milos.stojanovic/git_projects/pdf-generator/miticon-pdf-service/target/pdf-generator-0.0.1-SNAPSHOT.jar
RUN mkdir -p opt/pdf-app/resources
COPY ${JAR_FILE} opt/pdf-app/pdf-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/pdf-app/pdf-app.jar"]
