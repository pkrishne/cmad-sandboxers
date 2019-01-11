FROM  maven as build
  

WORKDIR /opt/

COPY . /opt/

RUN  mvn package

FROM java

WORKDIR /opt

EXPOSE 8080

COPY --from=build /opt/target/cmad-sandboxers.jar .

CMD "java -jar cmad-sandboxers.jar"
