#!/bin/bash

NAME=$1

if [[ `docker ps | grep ${NAME}` ]];
then
    docker stop ${NAME}
fi;

docker rm ${NAME}
docker rmi ${NAME}

#. 代表当前目录
docker build -t ${NAME} --build-arg NAME=${NAME} .
docker run -d --name ${NAME} ${NAME}
