FROM java:9-jdk

MAINTAINER alois.pin@softeam.fr

WORKDIR /apps/evenement-rappel

COPY target/evenement-rappel.jar /apps/evenement-rappel/evenement-rappel.jar

EXPOSE 8080

CMD java -jar evenement-rappel.jar