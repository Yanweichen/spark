FROM anapsix/alpine-java:latest
COPY spark_demo.jar /opt/
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "spark_demo.jar", "&"]