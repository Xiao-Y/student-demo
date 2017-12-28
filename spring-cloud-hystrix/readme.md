断路器（Hystrix）
依赖：ribbon
<dependency>
    <groupId>org.springframework.cloud</groupId> <br>
    <artifactId>spring-cloud-starter-ribbon</artifactId> <br>
</dependency>

<br>

 <!-- Circuit Breaker: Hystrix Dashboard (断路器：hystrix 仪表盘) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
<br>
访问：http://localhost:8766/hystrix <br>
输入：http://localhost:8766/hystrix.stream

<br>
1.先启动spring-eureka-server项目中的EurekaServerApplication <br>
2.再启动spring-eureka-client项目中的EurekaClientApplication <br>

访问：
http://localhost:8766/hi?name=xiaoy <br>
可以看到相应的信息 <br>

停止EurekaClientApplication <br>
访问： 
http://localhost:8766/hi?name=xiaoy <br>
可以看到相应的信息 <br>