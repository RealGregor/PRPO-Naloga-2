FROM adoptopenjdk:15-jre-hotspot
RUN mkdir /app
WORKDIR /app
ADD ./target/lokacijski-opomniki-rezervacije-1.0.0-SNAPSHOT.jar /app
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "lokacijski-opomniki-rezervacije-1.0.0-SNAPSHOT.jar"]