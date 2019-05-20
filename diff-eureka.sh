#!/bin/bash -il

file1=`wc -c /home/chenjia/applications/lxt-eureka-1.0-SNAPSHOT.jar | awk '{print $1}'`
file2=`wc -c lxt-eureka/target/lxt-eureka-1.0-SNAPSHOT.jar | awk '{print $1}'`

if [ ${file1} -eq ${file2} ]
then
    echo 00
else
    echo 99
fi
