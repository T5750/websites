# renren-fast

## Runtime Environment
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Spring Boot 2.0.3.RELEASE](https://projects.spring.io/spring-boot)
- [Spring Framework 5.0.7.RELEASE](http://projects.spring.io/spring-framework)
- [MyBatis 3.4](http://www.mybatis.org/mybatis-3/)
- [Shiro 1.4.0](http://shiro.apache.org/download.html)
- [Quartz 2.3.0](http://www.quartz-scheduler.org/)
- [Druid 1.1.10](https://github.com/alibaba/druid)
- [Redis 2.9.0](https://redis.io)
- [MySQL 5.6](http://www.mysql.com/)

## Introduction
- renren-fast是一个轻量级的，前后端分离的Java快速开发平台，能快速开发项目并交付【接私活利器】
- 支持MySQL、Oracle、SQL Server、PostgreSQL等主流数据库

## Project Structure
```
renren-fast
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─RenrenApplication 项目启动类
│  
└──resources 
   ├─mapper SQL对应的XML文件
   └─static 静态资源
```

## How to play
### 后端部署
- 通过git下载源码
- 创建数据库renren_fast，数据库编码为UTF-8
- 执行db/mysql.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行RenrenApplication.java，则可启动项目
- Swagger路径：http://localhost:8080/renren-fast/swagger/index.html

### 前端部署
- 本项目是前后端分离的，还需要部署前端，才能运行起来
- 前端下载地址：https://github.com/daxiongYang/renren-fast-vue
- 前端部署文档：https://github.com/daxiongYang/renren-fast-vue/wiki/Getting-started
- 前端部署完毕，就可以访问项目了，账号：admin，密码：admin

## Results
![menu](https://gitee.com/uploads/images/2018/0505/173115_d3c045ef_63154.jpeg)
![swagger](http://cdn.renren.io/img/6e8d7575fb8240d49b949dc0f02547bc)

## Tips
- Commits on 2018-06-24
- Modified:
	- `pom.xml`
	- `application-dev.yml`

## Links
- [renren-fast](https://gitee.com/renrenio/renren-fast)