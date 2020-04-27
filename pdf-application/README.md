Compile a project:
1. git clone "git@github.com:milosstojanovic2318s/maven-applications.git"
2. cd pdf-application/
3. mvn compile

Create a jar:
1. mvn clean package -DskipTests

Run application:
1. Make sure port 8080 is available
2. java -jar pdf-generator-0.0.1-SNAPSHOT.jar
