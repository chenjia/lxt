#!/bin/bash -il
file1=`wc -c /home/chenjia/applications/lxt-push.war | awk '{print $1}'`
file2=`wc -c lxt-push/target/lxt-push-1.0-SNAPSHOT.war | awk '{print $1}'`
if [[ ${file1} == ${file2} ]]
then echo 00
else echo 99
fi
