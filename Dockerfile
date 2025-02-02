FROM gradle:8.3-jdk17 AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle gradle

RUN gradle build --no-daemon -x test || return 0

COPY . .

RUN ./gradlew bootJar --no-daemon -x test

FROM openjdk:17-slim

ENV PORT=8080

COPY --from=builder /app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080