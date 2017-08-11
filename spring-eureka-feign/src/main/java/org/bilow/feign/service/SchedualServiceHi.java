package org.bilow.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/8/10.<br/>
 * Feign中使用断路器<br/>
 * 如果你使用了feign，feign是自带断路器的，并且是已经打开了。如果使用feign不想用断路器的话，可以在配置文件中关闭它，配置如下：<br/>
 * feign.hystrix.enabled=false<br/>
 * http://localhost:8764/hystrix,
 */
@FeignClient(value = "spring-eureka-client", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
