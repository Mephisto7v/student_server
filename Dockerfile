FROM openjdk:17-oracle
COPY target/studentServer.jar studentServer.jar
ENTRYPOINT ["java","-jar","/studentServer.jar"]