# seckill

## Runtime Environment
 - [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
 - [Spring Framework 4.1.7.RELEASE](http://projects.spring.io/spring-framework)
 - [MyBatis 3.3.0](http://www.mybatis.org/mybatis-3/)
 - [MySQL 5.5](http://www.mysql.com/)
 - [Redis 2.7.3](https://redis.io)
 - [protostuff 1.0.8](http://www.protostuff.io/)

## How to play

 1. git clone `https://github.com/T5750/websites.git`
 2. open IDEA -->  File  -->  New  --> Open
 3. choose seckill's pom.xml, open it
 4. update the `jdbc.properties` files about your mysql's username and password
 5. deploy the tomcat, and start up
 6. enter in the browser: `http://localhost:8080/seckill/list`
 7. enjoy it

## Changes

### DB
added `seckill\src\main\sql\seckill_20171107.sql`

### Spring
modified `seckill\src\main\resources\spring\spring-dao.xml`
```
	<!--<context:property-placeholder location="classpath:jdbc.properties"/>-->
    <!--java.sql.SQLException: Access denied for user 'Administrator'@'localhost-->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:jdbc.properties"/>
    </bean>
```

### Test
added exportSeckillUrl() for `seckill\src\test\java\cn\codingxiaxw\service\SeckillServiceTest.java`

## Tips

- Commits on Jul 18, 2017

## Links
[seckill](https://github.com/codingXiaxw/seckill)