# Jenkins

## Java
- `yum install java`
- `java --version`

## Git
- `yum install git`
- `git --version`
- 将Git初始化并生成授信证书
	```
	git config --global user.name "用户名"
	git config --global user.email "邮箱"
	ssh-keygen -t rsa -C "邮箱"
	（全部直接回车默认）
	```
- 验证是否生成：`ls ~/.ssh/`

### GitHub
1. Personal settings
1. SSH and GPG keys: New SSH keys 
1. `cat ~/.ssh/id_rsa.pub`
1. `ssh git@github.com`: You've successfully authenticated

## Maven
1. `cd /usr/local/software`
1. `wget http://mirrors.hust.edu.cn/apache/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.zip`
1. `unzip apache-maven-3.5.3-bin.zip`
1. `vim /etc/profile`
	```
	export MAVEN_HOME=/usr/local/apache-maven-3.6.0
	export PATH=$MAVEN_HOME/bin:$PATH
	```
1. `mvn -v`

## Tomcat
1. `tar -zxvf apache-tomcat-8.5.38.tar.gz -C /usr/local/`
1. `vim conf/server.xml`
	```
	<Connector port="8090" protocol="HTTP/1.1"
		connectionTimeout="20000"
		redirectPort="8443" URIEncoding="UTF-8" />
	```
1. `bin/startup.sh`
1. `tail -f -n 99 /usr/local/apache-tomcat-8.5.38/logs/catalina.out`

## Getting Started
1. Download Jenkins 2.150.3: [Generic Java package (.war)](http://mirrors.jenkins.io/war-stable/latest/jenkins.war)
1. `java -jar jenkins_2.150.3.war --httpPort=8080 &`
1. [http://localhost:8080](http://localhost:8080)
1. Unlock Jenkins: `/root/.jenkins/secrets/initialAdminPassword`
1. Customize Jenkins: Install suggested plugins
1. Create First Admin User: admin
1. Instance Configuration: http://192.168.100.211:8080/
1. 系统管理，插件管理：Rebuilder，Safe Restart
1. 系统管理，全局安全配置：安全矩阵，Add user or group，admin，全选
1. 系统管理，管理用户，Create User。安全矩阵，Add user or group，tester，全选（除Administer外）

### 节点管理
1. 系统管理，节点管理，New Node，TestEnv，固定节点
1. Remote root directory: `/root/.jenkins`
1. Launch method: Launch agent agents via SSH
1. Host: 192.168.100.211
1. Credentials: root
1. Host Key Verification Strategy: Non verifying Verification Strategy

### TaskTest
1. New 任务
1. Enter an item name: TaskTest
1. 构建一个自由风格的软件项目
1. Restrict where this project can be run
1. Label Expression: TestEnv
1. Build，执行Shell，Command: `ifconfig`
1. 立即构建
1. Console Output

### TaskOrder
1. New 任务
1. Enter an item name: TaskOrder
1. 构建一个自由风格的软件项目
1. Restrict where this project can be run
1. Label Expression: TestEnv
1. Source Code Management: Git
1. Repository URL: `git@github.com:princeqjzh/order.git`
1. Additional Behaviours: Check out to a sub-directory
1. Local subdirectory for repo: order
1. Build，执行Shell，Command
	```
	BUILD_ID=DONTKILLME
	
	. /etc/profile
	
	#配置运行参数
	export PROJ_PATH=`pwd`
	export TOMCAT_APP_PATH=/usr/local/apache-tomcat-8.5.38
	
	sh $PROJ_PATH/order/deploy.sh
	```
1. 立即构建

## Links
- [Jenkins](https://jenkins.io/download/)
- [用Jenkins自动化搭建测试环境](https://www.imooc.com/learn/1008)
- [Jenkins用户手册](https://jenkins.io/zh/doc/)