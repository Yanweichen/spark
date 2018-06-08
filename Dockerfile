FROM anapsix/alpine-java:latest
ARG imageName
ARG projectPort
COPY $imageName.jar /opt/
ENV PROJECT_JAR_NAME=$imageName
EXPOSE $projectPort
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "$PROJECT_JAR_NAME.jar", "&"]