FROM openjdk:17
VOLUME /tmp
EXPOSE 9411
ADD ./zipkin-server-2.24.0-exec.jar zipkin-serve.jar
ENTRYPOINT ["java","-jar","/zipkin-serve.jar"]