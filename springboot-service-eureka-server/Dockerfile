FROM openjdk:17
VOLUME /tmp
EXPOSE 8761
ADD ./target/springboot-service-eureka-server-0.0.1-SNAPSHOT.jar config-eureka.jar
ENTRYPOINT ["java","-jar","/config-eureka.jar"]