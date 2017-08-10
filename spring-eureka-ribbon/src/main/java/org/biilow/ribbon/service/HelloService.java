package org.biilow.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/8/10.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        //spring.application.name
        return restTemplate.getForObject("http://spring-eureka-client/hi?name=" + name, String.class);
    }

}

