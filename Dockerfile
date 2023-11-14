FROM  java:11-jdk AS TEMP_BUILD_IMAGE
COPY . /tmp
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "/app.jar"]
