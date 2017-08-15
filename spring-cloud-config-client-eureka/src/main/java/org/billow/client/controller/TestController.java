package org.billow.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${mysqldb.datasource.url}")
    private String foo;

    @RequestMapping("/foo")
    public String foo() {
        return foo;
    }
}
