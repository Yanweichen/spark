#!/bin/bash

NAME=$1
PROJECT_PORT=$2
DOCKER_PORT=$3

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
    docker rm ${NAME}
    docker rmi ${NAME}
fi;

#. 代表当前目录
docker build -t ${NAME} --build-arg imageName=${NAME} --build-arg projectPort=${PROJECT_PORT} .
docker run -p ${PROJECT_PORT}:${DOCKER_PORT} --name ${NAME} ${NAME}
