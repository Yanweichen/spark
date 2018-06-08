FROM anapsix/alpine-java:latest
COPY ${imageName:-def_name}.jar /opt/
EXPOSE ${projectPort:-8001}
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "${imageName:-def_name}.jar", "&"]