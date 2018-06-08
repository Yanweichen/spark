FROM anapsix/alpine-java:latest
ARG imageName
ARG projectPort
COPY $imageName.jar /opt/
EXPOSE $projectPort
WORKDIR /opt/
RUN java -jar -Duser.timezone=GMT+8 $imageName.jar &