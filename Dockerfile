FROM  java:8-jdk AS TEMP_BUILD_IMAGE
COPY . /tmp
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "/app.jar"]
