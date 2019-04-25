package com.lxq.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients  //可以开启Fegin权限
@RestController //为了返回当前请求
public class SpringbootEurekaConsumerApp {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/")
    public String getHello(){

        return "this is consumer,我是消费者，springcloud 2.0" +
                "版本消费者,端口号："+serverPort;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEurekaConsumerApp.class, args);
    }

    //解决RestTemplate 找不到的原因，应该把restTemplate注册到Springboot容器中@Bean
    @Bean
    @LoadBalanced //开启客户端负载均衡（只要使用别名访问，必须开启），能让RestTemplate在请求时拥有客户端负载均衡能力
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
