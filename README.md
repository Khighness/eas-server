# 排考系统-后端



## 安装部署

🚀安装：`maven install`

💠打包：`maven package`

🌈生成：`docker -t build eas .`

🌠运行：`dokcker run -d -p 3333:3333 --name eas eas`



## 技术选型

|     技术      |       版本       |     描述     |
| :-----------: | :--------------: | :----------: |
|     Java      |        8         |   语言选择   |
| IntelliJ IDEA |     \> 2019      |   集成环境   |
|     MySQL     |       \> 8       |    数据库    |
|     Maven     |      \> 3.6      |   项目管理   |
|  SpringBoot   |  2.2.2.RELEASE   |   单体应用   |
|    Mybatis    |      2.1.1       |   持久框架   |
|     Redis     | 5.0.9-alpine3.11 |   数据缓存   |
|      JWT      |      3.9.0       |   安全策略   |
|    Lombok     |      1.8.12      |   开发插件   |
|   Mapstruct   |   1.3.0.Final    |   开发插件   |
| Mybatis-Plus  |      3.4.0       |   开发插件   |
|    HuTool     |      5.5.1       |   第三方库   |
|  SpringCloud  |    Hoxton.SR1    | 分布式微服务 |
|     Nacos     |  2.2.2.RELEASE   |   服务管理   |



## 环境构建

> 部署MySQL

```shell
$ docker pull mysql:8.0.20
$ mkdir -p /home/sql/mysql/data /home/sql/mysql/conf
$ docker run --name mysql -d -p 3306:3306 -v /home/sql/mysql/conf:/etc/mysql/conf.d -v /home/sql/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=<password> mysql:8.0.20
$ docker exec -it ksql  bash
$ mysql -u root -pKAG1823
$ ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '<password>';
```




> 部署Redis

```shell
$ mkdir -p /home/redis/node1/data /home/redis/node1/conf
$ cd /home/redis/node1/conf
$ touch redis.conf
$ cat << EOF >>/home/redis/node1/conf/redis.conf
port 6379
#bind 0.0.0.0
daemonize NO
protected-mode no
requirepass KAG1823
cluster-enabled no
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
$ docker run -d -p 6379:6379 --name redis -v /home/redis/node1/data:/data -v /home/redis/node1/conf/redis.conf:/etc/redis/redis.conf redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
```



## 开发规范

> 注释规范 

- 【强制】class/interface/enum/@interface头部注释

  ```java
  /**
    * <p> Project: ${PROJECT_NAME} </p>
    * <p> Package: ${PACKAGE_NAME} </p>
    * <p> FileName: ${NAME} <p>
    * <p> Description: <p>
    * <p> Created By ${PRODUCT_NAME} </p>
    *
    * @author ${USER}
    * @since ${DATE} 
    */
  ```

- 【强制】类的成员属性注释

```java
    /**
     * description
     */
```

- 【强制】方法头部注释，参考javadoc注释规范

  ```java
  /**
    * @apinote
    * @deprecated
    * @param
    * @throws
    * @exception
    * @return
    */
  ```

- 【参考】一般注释描述

  - 单行注释：/ description /
  - 多行注释：/* description */



> 开发规范

- 【强制】各层命名规约： 
  - 实体层：entity
    - 类的命名为大驼峰直接对应数据库下划线表名。
  - 传输层：dto
    - 类的命名为实体名+DTO。
  - 展示层：vo
    - 类的命名为实体层+VO。
  - 持久层：mapper 
    - 接口命名为实体名+Mapper，Mybatis文件命名为实体名+Mapper.xml。
  - 业务层：service
    - 接口命名为实体名+Service，实现类为实体名+ServiceImpl。
  - 控制层：controller
    - 类的命名为实体名+Controller。
  - 切面层：aspect
    - 类的命名为切面名+Aspect。
  - 配置层：config
    - 类的命名为配置内容+Config。
  - 拦截层：intercepter
    - 类的命名为拦截内容+Intercepter。
  - 过滤层：filter
    - 类的命名为过滤内容+Filter。
  - 常量层：constant
    - 类的命名为常量内容+enum。
  - 工具层：util
    - 类的命名为工具名称+Utils。
  - 通用层：common
    - 类的命名要描述清楚类的含义。
- 【强制】Service/Mapper层方法命名规约：
  -  获取单个对象的方法用 get 做前缀。 
  -  获取多个对象的方法用 list 做前缀，复数结尾。
  -  获取统计值的方法用 count 做前缀。 
  -  插入的方法用 save/insert 做前缀。 
  -  删除的方法用 remove/delete 做前缀。 
  -  修改的方法用 update 做前缀。 


