#!/usr/bin/env bash
ps -ef|grep name=courage |awk '{print "kill -9 ",$2}' |sh >/dev/null 2>&1
