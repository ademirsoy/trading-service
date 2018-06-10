FROM java:8
EXPOSE 9000
VOLUME /tmp
ADD /target/trading-service-0.0.1.jar app.jar
ADD /src/main/resources/application.yml application.yml
ADD /src/main/resources/schema.sql schema.sql
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


