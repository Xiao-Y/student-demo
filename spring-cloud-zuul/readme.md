1.先启动spring-eureka-server项目中的EurekaServerApplication <br>
2.再启动spring-eureka-client项目中的EurekaClientApplication <br>

访问： <br>
http://localhost:8769/api-a/hi?name=xiaoy <br>
http://localhost:8769/api-b/hi?name=xiaoy <br>
http://localhost:8769/api-a/hi?name=xiaoy&token=123 <br>
http://localhost:8769/api-b/hi?name=xiaoy&token=123 <br>
