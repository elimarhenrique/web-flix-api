FROM openjdk:latest

WORKDIR /usr/src/myapp

COPY /target/web-flix-api-0.0.1-SNAPSHOT.jar /usr/src/myapp

ENTRYPOINT [ "java", "-jar", "web-flix-api-0.0.1-SNAPSHOT.jar" ] 
