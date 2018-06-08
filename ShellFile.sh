#!/bin/bash

NAME=$1
PROJECT_DIR=$2
PROJECT_PORT=$3
DOCKER_PORT=$4

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
    docker rm ${NAME}
    docker rmi ${NAME}
fi;

#. 代表当前目录
docker build -t ${NAME} --build-arg imageName=${NAME} --build-arg projectDir=${PROJECT_DIR} .
docker run -d -p ${PROJECT_PORT}:${DOCKER_PORT} --name ${NAME} ${NAME}
