### 新建项目
Spring Cloud Discovery --> Eureka Discovery Client

### Eureka搭建
- 优先启动 eureka-server
- 启动 eureka-client 自动注册到server
- 启动 eureka-consumer，消费服务地址 http://SERVICE-CLIENT/timeout 
- 启动 feign-consumer，通过feign消费服务

### eureka监控台
http://localhost:8000

### 单独访问
http://localhost:8001/timeout?name=forez

### 链路跟踪
http://localhost:9411/zipkin

### 打开Hystrix Dashboard
http://localhost:8001/hystrix

### 输入查看监控信息
http://localhost:8001/actuator/hystrix.stream