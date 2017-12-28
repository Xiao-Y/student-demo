1.先启动spring-eureka-server项目中的EurekaServerApplication <br>
2.再启动spring-eureka-client项目中的EurekaClientApplication <br>

访问： 
http://localhost:8761/ <br>
可以查看到application中有SPRING-EUREKA-CLIENT说明已经注册成功 <br>

访问：
http://localhost:8761/hi?name=xiaoy <br>
可以看到相应的信息 <br>

访问：
http://localhost:8761/getWord <br>
可以看到相应的信息 <br>

访问：
http://localhost:8761/info <br>
可以看到相应的信息 <br>