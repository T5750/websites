# rocketmq

## Runtime Environment
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- [RocketMQ 3.2.6](https://github.com/apache/rocketmq)

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

## Results
![rocketmq](http://www.wailian.work/images/2018/06/05/rocketmq.png)

## Links
- [在Windows下搭建RocketMQ](https://blog.csdn.net/u014134180/article/details/51790988)