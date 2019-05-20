#!/bin/bash -l
file1=`wc -c /home/chenjia/applications/lxt-workflow-1.0-SNAPSHOT.jar | awk '{print $1}'`
file2=`wc -c lxt-workflow/target/lxt-workflow-1.0-SNAPSHOT.jar | awk '{print $1}'`
if [[ ${file1} == ${file2} ]]
then echo 00
else echo 99
fi
