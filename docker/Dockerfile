FROM openjdk:8-jdk-slim

ADD ./target/demo-sso.jar demo-sso.jar

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom  -jar /demo-sso.jar" ]

EXPOSE 11000