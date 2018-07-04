##基本命令安装
```shell
yum install vim
yum -y install epel-release  
yum -y install htop
yum -y install lrzsz
yum -y install wget
yum -y install curl
```

##安装git maven docker
```shell
yum install git
git --version
yum install maven
mvn -version
yum install docker
docker -v
```

##git 命令
```
git clone https://github.com/chenbobaoge/easyblogback.git
#进入文件夹
git pull
```

## maven docker 插件生产镜像
```
#导出jar
mvn package
#生成docker镜像
mvn package docker:build
```
##dockerfile
```
FROM java:8
MAINTAINER huangll99@126.com
ADD eurekaserver-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8761
ENTRYPOINT ["/usr/bin/java","-jar","app.jar"]
```

## docker 命令
```
docker login -u baogechen1985@163.com -p chenbo851106 registry.cn-hangzhou.aliyuncs.com
docker tag b0771835e652 registry.cn-hangzhou.aliyuncs.com/bobochen/mywei
docker push  registry.cn-hangzhou.aliyuncs.com/bobochen/mywei

docker rmi registry.cn-hangzhou.aliyuncs.com/bobochen/mywei
docker run -itd -p 8787:8761 --restart=always --name suerkaserver1 registry.cn-hangzhou.aliyuncs.com/bobochen/mywei
docker rm -f suerkaserver1
```

##shell获取随机数
```
echo $(((RANDOM %10000)+10000))
```

##shell判断端口是否被占用
```
netstat -tlnp|grep 80
```

##删除已经存在的docker 
```
docker ps|awk '{print $1}'|  while read line; do echo $line; echo 222; done
```
