# pact-spring-microservices
Spring Boot Microservices with Pact (Consumer Driven Contracts) and Pact Broker integration

### Build without Test
`mvn clean package -Dmaven.test.skip=true`

### Docker compose
`docker-compose -f docker-compose-pact-broker.yml up`

`docker-compose -f docker-compose-pact-broker.yml down`

`docker-compose -f docker-compose-pact-services.yml up --build`

`docker-compose -f docker-compose-pact-services.yml down`

### Build and Test: pact-consumer
`cd pact-consumer`

`mvn clean install pact:publish`

### Build and Test: pact-provider
`cd pact-provider`

`mvn clean install -Dpact.verifier.publishResults=true`