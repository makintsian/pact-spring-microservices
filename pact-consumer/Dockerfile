FROM openjdk:11

WORKDIR /usr/src

ADD target/pact-consumer*.jar pact-consumer.jar

ENTRYPOINT ["java", "-jar", "pact-consumer.jar"]