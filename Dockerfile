FROM amazoncorretto:17-alpine

RUN addgroup -g 1101 -S app && \
    adduser -u 1101 -S -G app app

WORKDIR /app
COPY --chown=1101:1101 target/nordic-weather*.jar ./nordic-weather.jar
USER 1101:1101
CMD ["java", "-jar", "nordic-weather.jar"]
