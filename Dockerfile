FROM anapsix/alpine-java:latest
COPY spark_demo.jar /opt/
EXPOSE 8001
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "spark_demo.jar", "&"]