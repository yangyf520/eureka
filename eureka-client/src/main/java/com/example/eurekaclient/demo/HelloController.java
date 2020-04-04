package com.example.eurekaclient.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者类
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @HystrixCommand(fallbackMethod = "fallbackError")
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        int i = 1/0;
        return "welcome " + name + ",your port:" + port;
    }

    public String fallbackError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
