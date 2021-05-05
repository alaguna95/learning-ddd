FROM gradle:7.0.0-jdk11@sha256:03d12000667b253f98f486614bd6a452ccf983f581c2495ad13533ae031a3685 AS build
WORKDIR /app
COPY . /app
RUN gradle build

FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine@sha256:def864ee30f6657d781712ffbef162ce5b1aaeeb963e6faa1732de7e71287083
WORKDIR /app
COPY --from=build /app/build/libs/learning-ddd-0.0.1-SNAPSHOT.jar /app/learning-ddd-0.0.1-SNAPSHOT.jar
CMD "java" "-jar" "learning-ddd-0.0.1-SNAPSHOT.jar"