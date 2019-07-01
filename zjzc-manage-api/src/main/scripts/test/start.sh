#!/bin/sh
#变量设置
SERVICE_NAME=zjzc-manage-api
mkdir -p /logs/$SERVICE_NAME
PROJECT_DIR=$(cd "$(dirname "$0")"; pwd)
PROJECT_DIR=$PROJECT_DIR/..
CONFIG_DIR=$PROJECT_DIR/config
LIB_DIR=$PROJECT_DIR/lib
LIB_JARS=`ls $LIB_DIR | grep .jar | awk '{print "'$LIB_DIR'/"$0}' | tr "\n" ":"`
MAIN_CLASS="com.zjzc.manage.api.PsManageApiMain"
# 设置classpath
#nohup java -classpath $CONFIG_DIR:$LIB_JARS $MAIN_CLASS &
nohup java -Dspring.profiles.active=test -classpath $CONFIG_DIR:$LIB_JARS $MAIN_CLASS >> /logs/$SERVICE_NAME/std-out.log 2>&1 &
