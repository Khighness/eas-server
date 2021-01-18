FROM java:8

MAINTAINER KHighness "parakovo@gamil.com"

VOLUME /tmp

COPY *.jar /app.jar

CMD ["--server.port=3333"]

EXPOSE 3333

ENTRYPOINT ["java", "-jar", "/app.jar"]
