FROM majiajue/jdk1.8
VOLUME /tmp
ADD courage-1.0.0-SNAPSHOT.jar /opt/app.jar
ADD resources /opt/resources
ADD config /opt/config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xms512m -Xmx1024m","-jar","/opt/app.jar"]
