package com.example.feign.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务消费者
 */
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    /**
     * 服务消费者
     * @param name
     * @return
     */
    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public String feign(@RequestParam String name) {
        return feignService.sayHi(name);
    }
}
