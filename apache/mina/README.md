# MINA

## Runtime Environment
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- [MINA 2.0.7](http://mina.apache.org/mina-project/downloads.html)
- [Spring Framework 4.2.1.RELEASE](http://projects.spring.io/spring-framework)

## MINA based Application Architecture
![mina_app_arch](http://www.wailian.work/images/2018/06/22/mina_app_arch.png)

基于MINA的应用被分为三层
- I/O Service - 执行实际的I/O
- I/O Filter Chain - 过滤/转换字节到期望的数据结构，反之亦然。
- I/O Handler - 这里编写实际的业务逻辑。

### Server Architecture
![Server_arch](http://www.wailian.work/images/2018/06/22/Server_arch.png)

### Client Architecture
![clientdiagram](http://www.wailian.work/images/2018/06/22/clientdiagram.png)

## Quick Start Guide
- `MinaTimeServer`
- ```telnet 127.0.0.1 9123```

### Try out the Time server
Client Output | Server Output
---- | ----
hello | Message written...hello
Thu Jun 21 15:47:54 CST 2018 | IDLE 1
		
## Links
- [Quick Start Guide](http://mina.apache.org/mina-project/quick-start-guide.html)
- [MINA用户指南](http://www.majunwei.com/category/201707231630518014/)
- [apache-mina-2.0.7 mina-example](https://github.com/apache/mina/tree/2.0.7)