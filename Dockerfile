FROM anapsix/alpine-java:latest
ARG imageName
ARG projectPort
COPY $imageName.jar /opt/
EXPOSE $projectPort
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "spark_demo.jar", "&"]