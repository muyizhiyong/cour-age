# courage
山腰上人太多了，记得去山顶上看看！


## 源码导入与构建
1. git clone https://github.com/muyizhiyong/cour-age.git 
2. 使用 gradle 6.4 版本， jdk 1.8_181，IDEA 2018.3 及以上
3. 导入后，等待 gradle 刷新依赖
4. 勾选 IDEA 设置中的 File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors -> Enable Annotation Processors

## 项目集成设施
- aop  切面编程
    - web请求日志
- auth 认证中心 
    - 登录
    - 刷新token
    - 退出 
- common 共享依赖
- handler 消息调用
    - 交易码分发消息
    - 自定义@CourageMessageMapping
- quartz 定时任务
    - 定时解析获取天气预报
- rabbitMq 消息套件
    - 消息发送
    - 消息监听
- mongoDB 文件数据库
    - 保存
    - 查询（单条）
    - 事务添加，双事务配置（默认采用mysql事务） 
    
## 项目核心依赖
|  名称      |    版本号    |  类型 |
|  ----     |   -------     | ---- |
|Springboot |2.3.1.RELEASE  |      |
|Springcloud|2.2.4.RELEASE |      |
|Springsecurity |5.3.3.RELEASE|    |
|oauth2     |2.3.4.RELEASE  |      |
|aop        |5.2.7.RELEASE  |      |
|knife4j    |2.0.2          |      |
|swagger    |2.9.2          |      |
|fastjson   |1.2.72         |  阿里巴巴    |
|logback    |1.2.3          |      |
|kettle     |6.1.0.1-196    |  数据抽取ETL |

## 项目核心组件
|  名称      |    版本号    |  类型         |
|  ----     |   -------     | ----        |
|rabbitMQ   |3.7.3          |  通讯中间件  |
|redis      |6.0.8          |  内存数据库  |
|mysql      |5.7            |  关系型数据库 |
|mongoDB    |4.2.9          |  文件数据库  |