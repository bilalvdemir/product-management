ARG APP_NAME=code
ARG APP_VERSION=0.0.1-SNAPSHOT

FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn clean install -DskipTests


FROM eclipse-temurin:21-jre-jammy
RUN useradd -r -u 10001 appuser && \
    apt-get update && apt-get install -y --no-install-recommends jq curl

WORKDIR /app
ARG APP_NAME
ARG APP_VERSION
COPY --from=build /workspace/target/${APP_NAME}-${APP_VERSION}.jar /opt/app/app.jar
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD curl -fsS http://localhost:8080/actuator/health | grep -q '"status":"UP"' || exit 1

USER appuser

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
