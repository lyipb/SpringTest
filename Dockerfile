FROM tomcat:latest
ADD target/springtest.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
