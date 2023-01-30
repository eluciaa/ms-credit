FROM openjdk:17-oracle
COPY target/*.jar ms-credit-1.0.0.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/ms-credit-1.0.0.jar"]