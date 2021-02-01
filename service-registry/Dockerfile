FROM openjdk:8

COPY ./target/service-registry-*.jar service-registry-0.0.1-SNAPSHOT.jar

EXPOSE 8761

CMD ["java","-jar","service-registry-0.0.1-SNAPSHOT.jar"]