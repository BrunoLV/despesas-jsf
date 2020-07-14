#
# Build 
#
FROM maven:3.6.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Server 
#
FROM openjdk:8
WORKDIR /app
COPY --from=build /home/app/target/despesas-jsf-thorntail.jar .
EXPOSE 8080

ADD wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh


ENTRYPOINT ["java", "-jar", "despesas-jsf-thorntail.jar"]