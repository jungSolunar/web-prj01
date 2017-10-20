#!/bin/bash

DIR=`dirname "$0"`
APP_HOME=`cd "$DIR"/../; pwd`
cd $APP_HOME
SCRIPT=$APP_HOME/bin/ui-server.sh
#JAVA_HOME=/usr/local/jdk
NAME=ui-server
USER=`id -un`

DEBUG_PORT=8003
DEBUG_OPT=-agentlib:jdwp=transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n
if [ $# -eq 2 ]; then
	if [ $2 = "debug" -o $2 = "DEBUG" ];then
		DEBUG=$DEBUG_OPT
	else
		DEBUG=""
	fi
else
	DEBUG=""
fi

# check envirionments
if [ -z $JAVA_HOME ]; then
        echo "JAVA_HOME is not set. Specify environment : JAVA_HOME"
        exit 1
fi

LOG_HOME='/var/log/athene-sdwan'
mkdir -p $LOG_HOME
retval=$?; if [ ! $retval -eq 0 ]; then
        echo "Cannot create log directory '"$LOG_HOME"'. Check permission."
        exit 1
fi

PID_FILE=$APP_HOME/$NAME.pid
platform=`uname -a | awk '{print $1}'`
case "$platform" in
    Linux)
        JSVC=$APP_HOME/bin/jsvc
        ;;

    Darwin)
        JSVC=$APP_HOME/bin/jsvc_mac
        ;;

    *)
        echo "Unknown platform : "$platform
        exit 1
        ;;
esac

scan_lib_dir(){
    dir=$1
    #echo scan_dir:$dir

    # add jar file to CLASSPATH
    for file in $dir/*.jar; do
        CLASSPATH=${CLASSPATH}:$file
    done

    # scan sub directory
    for x in $dir/*; do
        if [ -d $x ]; then
            scan_lib_dir $x
        fi
    done
}

CLASSPATH=$APP_HOME/conf/
CLASSPATH=$CLASSPATH:$APP_HOME/public/
scan_lib_dir $APP_HOME/lib

case "$1" in
    start)
        echo "'$NAME' is starting.."
        nohup java -jar $APP_HOME/lib/ui-server-1.0.jar > /dev/null 2>&1 & echo $! > $PID_FILE &
        #nohup java \
        #-Dpidfile=$PID_FILE \
        #-cp $CLASSPATH \
        #Application > /dev/null 2>&1 & echo $! > $PID_FILE &

        ret=$?
        if [ $ret -eq 0 ]; then
            echo "'$NAME' is starting.. Done"
        else
            echo "'$NAME' is starting.. Failed"
        fi
        exit $ret
        ;;

    stop)
        echo "'$NAME' is stopping.."
        kill -9 `cat $PID_FILE` > /dev/null 2>&1

        ret=$?
        if [ $ret -eq 0 ]; then
            rm -f $PID_FILE
            echo "'$NAME' is stopping.. Done"
        else
            echo "'$NAME' is stopping.. Failed"
        fi
        exit $?
        ;;

    restart)
        $SCRIPT stop $2

        ret=$?;
        if [ $ret -eq 0 ]; then
            $SCRIPT start $2
            exit $?
        else
            exit $ret
        fi
        ;;

    *)
        echo "Usage : `basename $0` {start|stop|restart}"
        exit 1
        ;;
esac
