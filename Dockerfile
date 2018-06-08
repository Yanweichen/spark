FROM anapsix/alpine-java:latest
COPY build/libs/spark_demo.jar /opt/
EXPOSE 8001
WORKDIR /opt/
CMD ["java", "-jar", "-Duser.timezone=GMT+8", "spark_demo.jar", "&"]