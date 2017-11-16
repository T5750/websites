# Smart

## 简述
    Smart定位用当下最流行的SSM（SpringMVC + Spring + Mybatis）技术，为您构建一个易理解、高可用、高扩展性的单点登录权限管理应用基层，方便实现快速开发。权限按钮级（可控制到Controller中方法）、修改实时生效（MQ广播实现）、支持分布式（Spring + Redis提供分布式Session）。
    
## 组织结构

``` lua
smart
├── smart-mvc -- 公共核心模块（SpringMVC + Spring + Mybatis）
├── smart-sso -- 单点登录权限系统
├───── smart-sso-client -- 客户端依赖包，提供登录认证、授权管理
├───── smart-sso-demo -- 客户端
├───── smart-sso-server -- 服务端
├── smart-static -- 公用静态js、css文件
```

## 技术选型

### 后端
- JDK：1.8（支持1.6+）
- 数据库：Mysql
- 项目构建工具：Maven 3.3.3
- API文档：Springfox-Swagger2 2.6.1
- MVC框架：SpringMVC 4.2.1.RELEASE
- 核心框架：Spring 4.2.1.RELEASE
- ORM框架：MyBatis 3.3.0
- 分布式协调服务：Zookeeper 3.4.7
- 分布式RPC服务：Dubbo 2.5.3（默认Hessian 4.0.38）
- 分布式缓存服务：Redis 2.8.12
- 分布式消息服务：ActiveMQ 5.13.3
- NIO框架：Netty 4.0.23.Final
- JSON工具：Fastjson 1.2.29
- 定时任务：Quartz 2.2.1
- 数据库连接池：Druid 1.0.15
- 日志管理：SLF4J 1.7.21、Logback 1.1.7
- 模板引擎：Freemarker 2.3.23
- 单点登录：极简基于Cookie实现

### 前端
- 基础代码库：Jquery 2.1.1
- 前端模板：Ace 1.3.3(Bootstrap) https://github.com/bopoda/ace

## Urls
- http://localhost:8080/smart-sso-server
    - 默认的账号密码是：admin 123456
- http://localhost:8082/smart-sso-demo

## Tips
- Commits on Aug 11, 2017

## 原文传送门
[smart](https://github.com/a466350665/smart)