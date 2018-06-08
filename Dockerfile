FROM anapsix/alpine-java:latest
ARG imageName
ARG projectPort
COPY $imageName.jar /opt/
EXPOSE $projectPort
WORKDIR /opt/
ENV PROJECT_JAR_NAME=$imageName
RUN java -jar -Duser.timezone=GMT+8 $PROJECT_JAR_NAME.jar &