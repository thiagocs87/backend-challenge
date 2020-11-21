# BUILD
FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /build
COPY api/pom.xml api/pom.xml
COPY apigateway/pom.xml apigateway/pom.xml
COPY authorization/pom.xml authorization/pom.xml

COPY api/src api/src
COPY apigateway/src apigateway/src
COPY authorization/src authorization/src

WORKDIR /build/api
RUN mvn dependency:go-offline
RUN mvn package --batch-mode

WORKDIR /build/apigateway
RUN mvn dependency:go-offline
RUN mvn package --batch-mode

WORKDIR /build/authorization
RUN mvn dependency:go-offline
RUN mvn package --batch-mode

## RELEASE
FROM adoptopenjdk/openjdk11:jre-11.0.4_11-alpine
ARG app
COPY --from=build /build/authorization/target/authorization.jar /target/
COPY --from=build /build/api/target/api.jar /target/
COPY --from=build /build/apigateway/target/apigateway.jar /target/
WORKDIR /target
CMD ["sh", "-c", "$ME_RUN"]