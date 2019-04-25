package com.lxq.springboot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @Author Ncish
 * @Description
 * @Date 12:33 2019-04-16
 * @Modified By：
 * Company http://www.66ac.com
 */

@RestController
public class HelloController {
    /**
     * 在SpringCloud中有两种方式调用 rest , fegin(SpringCloud)
     * @return
     */

    //RestTemplate是有Springboot web组件提供，默认整合ribbon负载均衡器
    //rest方式底层是采用httpclient技术
    @Autowired
    private RestTemplate restTemplate;

    //调用提供者
    @RequestMapping("/getConsumer")
    public String getConsumer(){
        //调用有两种方式，1、采用服务器别用调用 2、直接地址调用
        //通过别名去注册服务中心获取对应的服务调用地址
        String url = "http://APP-PROVIDER/getProvider";
        String result = restTemplate.getForObject(url,String.class);
        //第二种直接调用地址
        //String result = restTemplate.getForObject("http://127.0.0.1:2222/getProvider",String.class);

        System.out.println("调用提供者服务result:"+ result);
        return result;
    }
}
