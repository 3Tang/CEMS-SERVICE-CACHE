#!/bin/sh
#version:1.0
#author:zhaodj
#mail: zhaodongjie@vrv.com.cn
#todo:uninstall the service 
#date:2015-07-16 12:09:08
#define the variable section
CURRPATH=$PWD
SERVICEPATH=${CURRPATH:19}
SERVICENAME=${SERVICEPATH%%/*}

LINKDIR="/etc/rc.d/init.d"  

#DESTPATH:target dir
DESTPATH="/usr/local/service/$SERVICENAME"
echo "###[1]ckeck the service###"
echo "###[1.1] ckeck the service process ID number###"
PRO_ID=`ps -ef | grep $SERVICENAME | grep -v grep | awk '{print $2}'`
echo "###[1.2]PDI:$PRO_ID"
if [ ! -n $PRO_ID ]
then
	 echo "###[1.2]service is running! stop it now!###"
	 
	 kill -9 $PRO_ID
	 if [ $? -eq 0 ]
	 then
		echo "###[1.3]stop service success!###"
	 else
		echo "###[1.4]stop service fail!###"
	 fi
else
	echo "###[1.5]service is not running now !"
fi

echo "###[2] drop the file in  the  soft link###"
# handle and operation section
LIST=`ls $LINKDIR`
for FILE in $LIST
do
	case $FILE in
		$SERVICENAME)
			echo "###[2.1]check it! file name is : $FILE###"
			COM="rm -rf $LINKDIR/$SERVICENAME"
			eval $COM
			if [ $? == 0 ]
			then
				echo "###[2.2]soft link file is droped success!###"
			else
				echo "###[2.2]soft link file is droped fail!###"
			fi
		;;
		*)
#			echo "current file name is : $FILE "
		;;
	esac
done

echo "###[3]drop the file of service###"
#isNullDir function
#return :1-exists,0-not exists
isNullDir()
{
	local NewCurrentPath=$PWD
	local NewDirName=$1
	cd $NewDirName
	local NewFileList=`ls 2>/dev/null` 
	
#	-n string : string is null 	

	if [ -n "$NewFileList" ]
	then
		echo "###[3]dir:$NewDirName list info : $NewFileList"
		cd $NewCurrentPath
		return 1 
	else
		echo "###[3]dir:$NewDirName is null"
		cd $NewCurrentPath
		return 0	
	fi	
}

if [[ "vrv" != "$LOGNAME" && "root" != "$LOGNAME" ]]
then
	echo "###[3]You do not have execute permission!Please contact your Manager!"
	exit 1	
fi

cd $DESTPATH 2>/dev/null

if [ $? -ne 0 ]
then
	echo "###no such directory!"
	exit 1
fi

echo "###[3]preparing to remove the directory: $DESTPATH"


FileList=`ls 2>/dev/null`

while [ "" != "$FileList" ]
do
	echo "###[3]current list info : $FileList"
	
	for pFile in $FileList
	do
		echo "###[3]read ${pFile} ......"
		if [ -d "${pFile}" ]
		then
			echo "###[3] ${pFile} is directory "
			echo "###[3]directory ${pFile} handle......"
			
	
			if  isNullDir "${pFile}"
			then
				echo 　"###[3]drop directory ${pFile} ......"
				rm -rf ${pFile}
			else
				echo "###[3]remove all file of ${pFile}......"
				mv ${pFile}/* . >/dev/null 2>&1
				if [ $? -ne 0 ]
				then
					ModifyTime=`date +%Y%m%d%H%M%S`  
					mv ${pFile} $ModifyTime 
				fi	
			fi
		else
			echo "###[3] ${pFile} is a file  "
			echo "###[3] rename ${pFile} for 1 ......"
			mv ${pFile} 1 2>/dev/null
		fi

		if [ -f 1 ]
		then
			echo "###[3]check the file named 1,droping ......"
			echo "###123456"> 1
			rm -rf 1		
		fi
	done
	rm -rf ${DESTPATH}
	if [ $? -eq 0 ]
	then
		echo "###[3]update the file list......"
		FileList=`ls 2>/dev/null`
	fi
done				

if [ $? -eq 0 ]
then
	echo "###[3]clear success!"
	exit 0
else
	echo "###[3]clear fail!"
	exit 1
fi

