FROM anapsix/alpine-java:latest
ARG imageName
COPY $imageName.jar /opt/
WORKDIR /opt/
CMD java -jar -Duser.timezone=GMT+8 $imageName.jar