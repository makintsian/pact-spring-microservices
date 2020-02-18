FROM openjdk:11

WORKDIR /usr/src

ADD target/pact-provider*.jar pact-provider.jar

ENTRYPOINT ["java", "-jar", "pact-provider.jar"]