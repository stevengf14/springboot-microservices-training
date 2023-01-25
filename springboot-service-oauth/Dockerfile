FROM openjdk:17
VOLUME /tmp
ADD ./target/springboot-service-oauth-0.0.1-SNAPSHOT.jar service-oauth.jar
ENTRYPOINT ["java","-jar","/service-oauth.jar"]