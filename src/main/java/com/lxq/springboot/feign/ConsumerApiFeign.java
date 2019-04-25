package com.lxq.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Ncish
 * @Description
 * @Date 2019-04-20 11:23
 * @Modified By：
 * Company http://www.66ac.com
 */
@FeignClient(name = "app-provider")  //调用服务接口
public interface ConsumerApiFeign {
    //Fegin 书写方式以SpringMVC接口形式书写

    @RequestMapping("/getProvider")
    public String getHello();
}
