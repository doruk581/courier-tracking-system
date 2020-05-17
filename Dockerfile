FROM adoptopenjdk/openjdk11:jre-11.0.2.9-alpine
RUN mkdir courier-tracking-system
ADD build/libs/courier-tracking-system-all-0.0.1-SNAPSHOT.jar courier-tracking-system/courier-tracking-service-all-0.0.1-SNAPSHOT.jar
WORKDIR courier-tracking-system
RUN "pwd"
RUN "ls"
EXPOSE 4567
ENTRYPOINT ["java","-jar", "courier-tracking-service-all-0.0.1-SNAPSHOT.jar"]
