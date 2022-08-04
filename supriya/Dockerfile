
FROM tomcat:8.0-alpine

LABEL maintainer=”lavanyan889737@gmail.com”

ADD gameoflife-build/target/gameoflife-build-1.0-SNAPSHOT.jar  /usr/local/tomcat/webapps/

EXPOSE 8080

CMD [“catalina.sh”, “run”]
