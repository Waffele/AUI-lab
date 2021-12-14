FROM openjdk:13

COPY ./target/demo-0.0.1-SNAPSHOT.jar aui.jar
EXPOSE 80
EXPOSE 8080

ENTRYPOINT java -jar aui.jar