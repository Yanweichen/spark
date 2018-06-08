FROM anapsix/alpine-java:latest
COPY ${imageName:no_name_project}.jar /opt/
EXPOSE 8001
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "${imageName:no_name_project}.jar", "&"]