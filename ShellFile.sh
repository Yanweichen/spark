#!/bin/bash

NAME=$1

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
    docker rm ${NAME}
    docker rmi ${NAME}
fi;

#. 代表当前目录
docker build -t ${NAME} --build-arg NAME=${NAME} .
docker run -d --name ${NAME} ${NAME}
