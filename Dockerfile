FROM jdk:8-en_US.UTF-8
VOLUME /tmp
ADD courage-1.0.0-SNAPSHOT.jar /opt/app.jar
ADD resources /opt/resources
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xms512m -Xmx1024m","-jar","/opt/app.jar"]

# docker build -t 10.2.21.95:10001/treasury-brain:1.1.0-SNAPSHOT ./build/libs
