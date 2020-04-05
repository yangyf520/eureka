package com.example.feign.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/feign",method = RequestMethod.GET)
    public String feign(@RequestParam String name){
        return feignService.sayHi(name);
    }
}
