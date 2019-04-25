package com.lxq.springboot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author Ncish
 * @Description 手动负载均衡底层原理
 * PS:如要启用此类，请屏蔽启动类里面的负载均衡 @LoadBalanced
 * @Date 2019-04-19 19:23
 * @Modified By：
 * Company http://www.66ac.com
 */

@RestController
public class ExtRibbonController {
    //可以获取注册中心上的服务列表
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    //接口请求总数
    private int reqCount = 1;

    @RequestMapping("/ribbonConsumer")
    public String ribbonProvider(){
        //获取对应服务器的远程调用地址
        String instancesUrl = getInstances()+ "/getProvider";
        System.out.println("instancesUrl: "+instancesUrl);
        //可以直接使用httpclient技术实现远程调用，restTemplate底层就是httpclient
        String result = restTemplate.getForObject(instancesUrl,String.class);
        //再使用rest方式发送请求
        return result;
    }

    private String getInstances(){
        List<ServiceInstance> instances = discoveryClient.getInstances("app-provider");
        if(null == instances || instances.size() <= 0){
            return null;
        }
        //获取服务器集群个数
        int instancesSize = instances.size();
        int serviceIndex = reqCount%instancesSize;
        reqCount++;
        return instances.get(serviceIndex).getUri().toString();
    }
}
