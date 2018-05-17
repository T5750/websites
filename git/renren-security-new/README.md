# renren-security

## Runtime Environment
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Spring Boot 2.0.1.RELEASE](https://projects.spring.io/spring-boot)
- [Spring Framework 5.0.5.RELEASE](http://projects.spring.io/spring-framework)
- [MyBatis 3.4.5](http://www.mybatis.org/mybatis-3/)
- [Shiro 1.3.2](http://shiro.apache.org/download.html)
- [Quartz 2.3.0](http://www.quartz-scheduler.org/)
- [Druid 1.1.9](https://github.com/alibaba/druid)
- [Redis 2.9.0](https://redis.io)
- [MySQL 5.6](http://www.mysql.com/)

## Introduction
- 采用SpringBoot、MyBatis、Shiro框架，开发的一套权限系统，极低门槛，拿来即用。设计之初，就非常注重安全性，为企业系统保驾护航，让一切都变得如此简单。
- 提供了代码生成器，只需编写30%左右代码，其余的代码交给系统自动生成，可快速完成开发任务
- 支持MySQL、Oracle、SQL Server、PostgreSQL等主流数据库

## Project Structure
```
renren-security
├─renren-common     公共模块
├─renren-admin      管理后台
│    ├─db  数据库SQL脚本
│    ├─modules  模块
│    │    ├─job 定时任务
│    │    ├─oss 文件存储
│    │    └─sys 系统管理(核心)
│    └─resources 
│        ├─mapper   MyBatis文件
│        ├─statics  静态资源
│        ├─template 系统页面
│        │    ├─modules      模块页面
│        │    ├─index.html   AdminLTE主题风格（默认主题）
│        │    └─index1.html  Layui主题风格
│        └─application.yml   全局配置文件
├─renren-api        API服务
└─renren-generator  代码生成器
         └─resources 
            ├─mapper   MyBatis文件
            ├─template 代码生成器模板（可增加或修改相应模板）
            ├─application.yml    全局配置文件
            └─generator.properties   代码生成器，配置文件
```

## How to play
- 创建数据库renren_security，编码为UTF-8
- 执行`db/mysql.sql`文件，初始化数据【按需导入表结构及数据】
- 修改`application-dev.yml`文件，更新MySQL账号和密码
- 在renren-security目录下，执行mvn clean install

### renren-admin
- `AdminApplication.java`
- renren-admin访问路径：[http://localhost:8080/renren-admin](http://localhost:8080/renren-admin)
- swagger文档路径：[http://localhost:8080/renren-admin/swagger/index.html](http://localhost:8080/renren-admin/swagger/index.html)
- 账号密码：admin/admin

### renren-api
- `ApiApplication.java`
- renren-api访问路径：[http://localhost:8081/renren-api/swagger-ui.html](http://localhost:8081/renren-api/swagger-ui.html)

### renren-generator
- `GeneratorApplication.java`
- renren-generator访问路径：[http://localhost:8082/renren-generator](http://localhost:8082/renren-generator)

 **分布式部署**
- 分布式部署，需要安装redis，并配置`config.properties`里的redis信息
- 需要配置`renren.redis.open=true`，表示开启redis缓存
- 需要配置`renren.shiro.redis=true`，表示把shiro session存到redis里

## Results
![menu](http://cdn.renren.io/img/c94be5b4bf0d4387b18e119c91b1a986)
![swagger](http://cdn.renren.io/img/c8dae596146248d8b4d0639738c2932b)

## Tips
- Commits on Apr 25, 2018
- Modified:
	- `pom.xml`
	- `application-dev.yml`

## Links
- [renren-security](https://github.com/renrenio/renren-security)