###Eureka搭建
- 优先启动 eureka-server
- 启动 eureka-client 自动注册到server
- 启动 eureka-consumer，通过http://SERVICE-CLIENT/hi消费服务 

打开Hystrix Dashboard
http://localhost:8001/hystrix

输入查看监控信息
http://localhost:8001/actuator/hystrix.stream