#!/bin/bash

NAME=$1
PROJECT_PORT=$2
DOCKER_PORT=$3

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
    docker rm ${NAME}
    docker rmi ${NAME}
    echo "------------docker images is rm finish------------"
fi;

#. 代表当前目录
docker build --no-cache -t ${NAME} --build-arg imageName=${NAME} .
#DOCKER_PORT 为宿主机端口 PROJECT_PORT 为容器内部端口
docker run -d -p ${DOCKER_PORT}:${PROJECT_PORT} --name ${NAME} ${NAME}
