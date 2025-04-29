FROM eclipse-temurin:21.0.6_7-jdk

WORKDIR /app

COPY .gradle/ /.gradle
COPY build ./build
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src

CMD ["./gradlew", "bootRun"]