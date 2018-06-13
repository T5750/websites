# RocketMQ

## Runtime Environment
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- [RocketMQ 3.2.6](https://github.com/apache/rocketmq)
- [rocketmq-console SNAPSHOT-1.0](https://github.com/apache/rocketmq-externals)
- [RocketMQ-Console-Ng 1.0.0](https://github.com/apache/rocketmq-externals)

## How to play
### RocketMQ Architecture
![rmq-basic-arc](https://rocketmq.apache.org/assets/images/rmq-basic-arc.png)

### Windows
进入`alibaba-rocketmq\bin`目录

- 启动name server
	- `mqnamesrv.exe`，启动name server，保持`mqnamesrv.exe`运行，不要关闭这个终端。
- 启动broker
	- 先输入`set NAMESRV_ADDR=127.0.0.1:9876`设置环境变量，输入`mqbroker.exe`启动broker，保持`mqbroker.exe`运行，不要关闭这个终端。 
	- 也可一步输入`mqbroker -n 127.0.0.1:9876`启动broker，保持`mqbroker.exe`运行，不要关闭这个终端。
- 初赛的DEMO，并解压```git clone https://code.aliyun.com/MiddlewareRace/PreliminaryDemo.git```
	- `Producer`
	- `Consumer`
	- ```java -Drocketmq.namesrv.addr=127.0.0.1:9876 -cp preliminary.demo-1.0-SNAPSHOT.jar com.alibaba.middleware.race.rocketmq.Producer```
	- ```java -Drocketmq.namesrv.addr=127.0.0.1:9876 -cp preliminary.demo-1.0-SNAPSHOT.jar com.alibaba.middleware.race.rocketmq.Consumer```

## Spring Boot
- `RocketmqApplication`
 
## RocketMQ-Console-Ng
### DOCKER 安装
1、获取 Docker 镜像
```
docker pull styletang/rocketmq-console-ng
```
2、运行，注意将你自己的 NameServer 地址替换下面的 127.0.0.1
```
docker run -e "JAVA_OPTS=-Drocketmq.namesrv.addr=127.0.0.1:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8080:8080 -t styletang/rocketmq-console-ng
```
### 非 DOCKER 安装
我们 git clone 一份代码到本地：
```
git clone https://github.com/apache/rocketmq-externals.git

cd rocketmq-externals/rocketmq-console/
```
需要 jdk 1.7 以上。 执行以下命令：
```
mvn spring-boot:run
```
或者
```
mvn clean package -Dmaven.test.skip=true

java -jar target/rocketmq-console-ng-1.0.0.jar
```
注意：
- 如果你使用的 RocketMQ 版本小于 3.5.8，如果您使用 rocketmq < 3.5.8，请在启动 rocketmq-console-ng 时添加 `-Dcom.rocketmq.sendMessageWithVIPChannel=false`（或者您可以在 ops 页面中更改它）
- 更改 `resource/application.properties` 中的 `rocketmq.config.namesrvAddr`（或者可以在ops页面中更改它）
```
rocketmq.config.namesrvAddr=localhost:9876
#if you use rocketmq version < 3.5.8, rocketmq.config.isVIPChannel should be false.default true
rocketmq.config.isVIPChannel=false
```

## Results
![rocketmq](http://www.wailian.work/images/2018/06/05/rocketmq.png)
![rocketmq-console](http://www.wailian.work/images/2018/06/06/rocketmq-console-min.png)
![RocketMQ-Console-Ng](http://www.wailian.work/images/2018/06/06/RocketMQ-Console-Ng-min.png)

## Links
- [在Windows下搭建RocketMQ](https://blog.csdn.net/u014134180/article/details/51790988)
- [SpringBoot RocketMQ 整合使用和监控](http://www.54tianzhisheng.cn/2018/02/07/SpringBoot-RocketMQ/)
- [Quick Start](https://rocketmq.apache.org/docs/quick-start/)
- [RocketMQ：一个纯java的开源消息中间件--开发测试环境搭建](https://my.oschina.net/cloudcoder/blog/200741)