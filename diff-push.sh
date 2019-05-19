#!/bin/bash
file1=`wc -c lxt-push.war | awk '{print $1}'`
file2=`wc -c lxt-push.war.old | awk '{print $1}'`
if [[ $file1 == $file2 ]]
then echo "00"
else echo "99"
fi
