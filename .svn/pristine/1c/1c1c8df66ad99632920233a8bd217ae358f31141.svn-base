#!/bin/sh
echo "#This script info[Todo:install the service, Version:1.0.20150819, updateTime:2015-08-19, Author:linwu_gao@163.com]"
echo "***** update info[ 1.set service fo self start; ]*****"

echo -n "############### 1.Please enter service name:"
	read serviceName
	echo "#### you input serviceName is [$serviceName] ####"
	echo "##### This is a ["$serviceName"] service ! #####"

echo "############### 2.Check JAVA environment ###############"
	java="$JAVA_HOME"
	if [ ${#java} < 0 ] 
	then
			echo "##### '$JAVA_HOME is not exists ！' #####"
			exit 1
	else
		echo "##### '$JAVA_HOME' is exists ! #####"
	fi

echo "############### 3.Update [../config/wrapper.conf] configure file ###############"
	FILE="../config/wrapper.conf"
	if [ ! -f $FILE ] 
	then 
		  echo "##### $FILE : does not exists #####" 
		  exit 1 
	elif [ ! -r $FILE ] 
	then       
			  echo "##### $FILE: can not read #####" 
			  exit 2 
	fi     
	key='wrapper.java.command'
	list=`cat $FILE`  
	for val in $list  
	do  	  
		key1=${val:0:20}
		if [ $key1 == $key ] 
		then
			echo "the line is :\n $val"
			JAVA_PATH=${JAVA_HOME//\//\\/}
			COM_STR="'s/wrapper.java.command=/wrapper.java.command="${JAVA_PATH}"\/bin\/java\n##/g'"
			COM="sed -i   $COM_STR $FILE"

			eval $COM
				if [ $? == 0 ]
				then
					echo "##### update '../config/wrapper.conf' configure file success! #####"
				else
					echo "##### update '../config/wrapper.conf' configure file fail! #####"
				fi
		fi
	done  

echo "############### 4.Begin create User vrv ... ###############"
	user=vrv
	group=vrv
	
	#create group if not exists
	egrep "^$group" /etc/group >& /dev/null
	if [ $? -ne 0 ]
	then
	    groupadd $group
	fi
	
	#create user if not exists
	egrep "^$user" /etc/passwd >& /dev/null
	if [ $? -ne 0 ]
	then
	    useradd -g $group $user
	fi
echo "############### 5.Create User vrv sucess. ###############"

echo "############### 6.Begin registe service ... ###############"
	chown -R vrv:vrv /usr/local/service
	chmod 775 /usr/local/service/$serviceName/bin/$serviceName 
	chmod 775 /usr/local/service/$serviceName/bin/wrapper
	ln -s /usr/local/service/$serviceName/bin/$serviceName /etc/init.d/$serviceName
	chown -R vrv:vrv /etc/init.d/$serviceName
	chmod 775 /etc/init.d/$serviceName
echo "############### 7.Registered service success. ###############"

echo "############### 8.Begin set service for self start... ###############"
	chkconfig $serviceName on
echo "############### 9.Set service for self start success. ###############"

echo "############### 10.Begin starting [$serviceName]. ###############"
    sudo -u vrv service $serviceName start
echo "############### 11.Start [$serviceName] success. ###############"

#exit
exit 0
