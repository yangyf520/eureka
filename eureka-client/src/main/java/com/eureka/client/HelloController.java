package com.eureka.client;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 生产者类
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFail")
public class HelloController {

    @Value("${server.port}")
    String port;

    /**
     * 超时请求
     *
     * @param name
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    @RequestMapping("/hi")
    public String hello(@RequestParam String name) {

        return "hi, " + name + " ,your port: " + port;
    }

    /**
     * 超时请求
     *
     * @param name
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    @RequestMapping("/timeout")
    public String timeout(@RequestParam String name) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "hi, " + name + " ,your port: " + port;
    }

    /**
     * 请求异常
     *
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500"),
                    // 滑动统计的桶数量
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    // 设置滑动窗口的统计时间。熔断器使用这个时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "15"),
                    // BlockingQueue的最大队列数，当设为-1，会使用SynchronousQueue，值为正时使用LinkedBlcokingQueue。
                    @HystrixProperty(name = "maxQueueSize", value = "15"),
                    // 设置存活时间，单位分钟。如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间.
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    // 设置队列拒绝的阈值,即使maxQueueSize还没有达到
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
            })
    @RequestMapping("/fall")
    public String fall(@RequestParam String name) {
        int i = 1 / 0;

        return "hello, " + name;
    }

    /**
     * 异常回调方法
     *
     * @param name
     * @return
     */
    public String fallbackError(String name) {
        return "hi, " + name + " ,error!";
    }

    /**
     * 默认回调方法
     *
     * @return
     */
    public String defaultFail() {

        return "default fail";
    }
}
