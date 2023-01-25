FROM openjdk:17
VOLUME /tmp
ADD ./target/springboot-service-items-0.0.1-SNAPSHOT.jar service-items.jar
ENTRYPOINT ["java","-jar","/service-items.jar"]