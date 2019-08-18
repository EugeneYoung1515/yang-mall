# yang-mall

  - 基于Spring Boot、Mybatis和PostgreSQL开发的小型分布式商城，使用Maven的聚合和继承功能用于组织代码和解决依赖。
  - 使用Nginx进行反向代理和动静分离，使用FreeMarker进行静态页生成，JSP和Redis用于动态页生成。
  - 搜索页主要使用PostgreSQL全文检索实现。
  - 基于Redis实现分布式Session，在分布式Session的基础上实现单点登录和登录信息共享。使用图片验证码和阿里云短信接口用于用户验证。
  - 使用Redis校验库存和预减库存，通过消息队列RabbitMQ异步发送消息，用于数据库实际减库存。
  - 使用Redis Lua脚本实现的具有超时特性的可重入分布式锁。用于处理Redis预减库存时的竞态条件，数据库悲观锁用于数据库减库存。
  - 基于RabbitMQ实现延时队列，使用Quartz实现定时任务，用于订单过期时返库存。
  - 使用第四方支付接口(XorPay)，实现微信支付和支付宝支付。