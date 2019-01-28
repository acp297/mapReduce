#!/bin/sh

mvn clean package
docker build -t imgur_upload:latest .
docker run --hostname=quickstart.cloudera --privileged=true -t -i -p 7180:7180 -p 8888:8888  imgur_upload:latest  /usr/bin/docker-quickstart