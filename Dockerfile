FROM  maven as build
  

WORKDIR /opt/

COPY . /opt/

RUN  mvn package

FROM java:8-jre-alpine

WORKDIR /opt

COPY --from=build /opt/target/*.jar app

EXPOSE 8080

CMD java -jar app


