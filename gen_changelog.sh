#!/bin/sh
if [ -e lastreport ]
then
	lastdate=`cat lastreport`
	git log --since master@{"$lastdate"}
else
	git log
fi
date > lastreport
