FROM anapsix/alpine-java:latest
ARG imageName
ENV PROJECT_JAR_NAME=$imageName
COPY $imageName.jar /opt/
WORKDIR /opt/
CMD java -Xms256m -Xmx1024m -jar -Duser.timezone=GMT+8 $PROJECT_JAR_NAME.jar