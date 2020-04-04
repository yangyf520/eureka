package com.example.eurekaconsumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者类
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 消费者
     *
     * @return
     */
    @RequestMapping("/say")
    public String say() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("name", "yyf");
        HttpEntity requestBody = new HttpEntity(param, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://SERVICE-CLIENT/hi", requestBody, String.class);
        String response = responseEntity.getBody();
        System.out.println(response);

        responseEntity = restTemplate.postForEntity("http://SERVICE-CLIENT/timeout", requestBody, String.class);
        response = responseEntity.getBody();
        System.out.println(response);

        responseEntity = restTemplate.postForEntity("http://SERVICE-CLIENT/fall", requestBody, String.class);
        response = responseEntity.getBody();
        System.out.println(response);

        return response;
    }

}
