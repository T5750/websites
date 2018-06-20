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
- 启动BROKER:
	- `start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true`
	- `start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true -c ../conf/broker.conf` (`broker.conf` added: `enablePropertyFilter=true`)

### Results
![rocketmq](http://www.wailian.work/images/2018/06/07/rocketmq-min.png)

### RocketMQ-Console-Ng
```
mvn clean package -Dmaven.test.skip=true

java -jar target/rocketmq-console-ng-1.0.0.jar
```

## RocketMQ-docs
- [官方文档]
	- [用户指南]
		- [1.为什么选择RocketMQ](官方文档/用户指南/1.为什么选择RocketMQ.md)
		- [2.快速开始](官方文档/用户指南/2.快速开始.md)
		- [3.简单消息示例](官方文档/用户指南/3.简单消息示例.md)
		- [4.有序消息示例](官方文档/用户指南/4.有序消息示例.md)
		- [5.广播消息示例](官方文档/用户指南/5.广播消息示例.md)
		- [6.计划消息示例](官方文档/用户指南/6.计划消息示例.md)
		- [7.批量消息示例](官方文档/用户指南/7.批量消息示例.md)
		- [8.过滤器示例](官方文档/用户指南/8.过滤器示例.md)
		- [9.日志附加器示例](官方文档/用户指南/9.日志附加器示例.md)
		- [10.OpenMessaging示例](官方文档/用户指南/10.OpenMessaging示例.md)
		- [11.常见问题](官方文档/用户指南/11.常见问题.md)
	- [部署和操作]
		- [1.架构](官方文档/部署和操作/1.架构.md)
		- [2.部署](官方文档/部署和操作/2.部署.md)
	- [最佳实践]
		- [1.核心概念](官方文档/最佳实践/1.核心概念.md)
		- [2.代理](官方文档/最佳实践/2.代理.md)
		- [3.生产者](官方文档/最佳实践/3.生产者.md)
		- [4.消费者](官方文档/最佳实践/4.消费者.md)
		- [5.名称服务器](官方文档/最佳实践/5.名称服务器.md)
		- [6.Java虚拟机和内核配置](官方文档/最佳实践/6.Java虚拟机和内核配置.md)

### Tips
- Commits on Oct 26, 2017
		
## Links
- [windows下RocketMQ安装部署](https://www.jianshu.com/p/4a275e779afa)
- [Quick Start](https://rocketmq.apache.org/docs/quick-start/)
- [rocketmq开发指南-v3.2.4](http://files.cnblogs.com/files/chenkaiwei/%E9%98%BF%E9%87%8CRocketMQ_%E7%94%A8%E6%88%B7%E6%8C%87%E5%8D%97_V3.2.4_%E6%9C%80%E6%96%B0%E7%89%88%E6%9C%AC.pdf)
- [RocketMQ 原理简介](http://alibaba.github.io/RocketMQ-docs/document/design/RocketMQ_design.pdf)
- [RocketMQ-docs](https://github.com/redzealot2008/RocketMQ-docs)