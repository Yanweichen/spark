FROM anapsix/alpine-java:latest
COPY ${NAME}.jar /opt/
EXPOSE 8001
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "${NAME}.jar", "&"]