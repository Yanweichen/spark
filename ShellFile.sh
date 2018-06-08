#!/bin/bash

NAME=$1
PROJECT_DIR=$2

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
    docker rm ${NAME}
    docker rmi ${NAME}
fi;

#. 代表当前目录
docker build -t ${NAME} --build-arg imageName=${NAME} --build-arg projectDir=${PROJECT_DIR} .
docker run -d --name ${NAME} ${NAME}
