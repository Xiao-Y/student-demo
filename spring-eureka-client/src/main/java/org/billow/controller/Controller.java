package org.billow.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class);

    @Value("${words}")
    String words;

    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryclient;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/getWord")
    public String getWord() {
        String[] wordArray = words.split(",");
        int i = (int) Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }

    @RequestMapping("/info")
    public String info() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("spring-eureka-client", false);
        String info = "/info, host:" + instance.getHostName() + ",serviceId:" + instance.getInstanceId();
        logger.info(info);
        return info;
    }

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }
}