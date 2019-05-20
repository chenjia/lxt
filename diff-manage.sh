#!/bin/bash

file1=`wc -c /home/chenjia/applications/lxt-manage-1.0-SNAPSHOT.jar | awk '{print $1}'`
file2=`wc -c lxt-manage/target/lxt-manage-1.0-SNAPSHOT.jar | awk '{print $1}'`

if [ ${file1} == ${file2} ]
then
    echo 00
else
    echo 99
fi
