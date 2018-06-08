FROM anapsix/alpine-java:latest
COPY ${projectDir:./}${imageName:default_project}.jar /opt/
EXPOSE 8001
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "${imageName:default_project}.jar", "&"]