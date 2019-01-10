FROM  maven
  

WORKDIR /opt/

COPY . /opt/

RUN  mvn package

EXPOSE 8080

CMD "java -jar target/cmad-sandboxers.jar"
