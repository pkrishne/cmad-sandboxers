FROM  maven as build
  

WORKDIR /opt/

COPY . /opt/

RUN  mvn package

FROM java:8

WORKDIR /opt

COPY --from=build /opt/target/*.jar .

COPY --from=build /opt/ .

CMD ["java -jar *.jar"]

EXPOSE 8080
