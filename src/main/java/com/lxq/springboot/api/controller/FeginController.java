package com.lxq.springboot.api.controller;

import com.lxq.springboot.feign.ConsumerApiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ncish
 * @Description
 * @Date 2019-04-20 12:53
 * @Modified By：
 * Company http://www.66ac.com
 */
@RestController
public class FeginController {

    @Autowired
    private ConsumerApiFeign consumerApiFeign;

    @RequestMapping("/feginConsumer")
    public String feginConsumer(){
        return consumerApiFeign.getHello();
    }
}
