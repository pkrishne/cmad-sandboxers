FROM  maven as build
  

WORKDIR /opt/

COPY . /opt/

RUN  mvn package

FROM java

WORKDIR /opt

COPY --from=build /opt/target/cmad-sandboxers.jar .

CMD "java -jar cmad-sandboxers.jar"

EXPOSE 8080
