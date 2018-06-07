# RocketMQ

## Runtime Environment
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- [RocketMQ 4.2.0](http://rocketmq.apache.org/release_notes/release-notes-4.2.0/)
- [RocketMQ-Console-Ng 1.0.0](https://github.com/apache/rocketmq-externals)

## How to play

### Windows下载
- 地址[RocketMQ 4.2.0](http://rocketmq.apache.org/release_notes/release-notes-4.2.0/)
- 选择`Binary`进行下载

### 系统环境变量配置
- 变量名：`ROCKETMQ_HOME`
- 变量值：`MQ解压路径\MQ文件夹名`

### 启动
Cmd命令框执行进入至`MQ文件夹\bin`
- 启动NAMESERVER: `start mqnamesrv.cmd`
- 启动BROKER: `start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true`

### Results
![rocketmq](http://www.wailian.work/images/2018/06/07/rocketmq-min.png)

### RocketMQ-Console-Ng
```
mvn clean package -Dmaven.test.skip=true

java -jar target/rocketmq-console-ng-1.0.0.jar
```

## Links
- [windows下RocketMQ安装部署](https://www.jianshu.com/p/4a275e779afa)
- [Quick Start](https://rocketmq.apache.org/docs/quick-start/)