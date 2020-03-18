package com.ruan.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ruan.service.client.UserClient;
import com.ruan.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("consumer/user/")
public class UserController {

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    @ResponseBody
//    @HystrixCommand(fallbackMethod = "queryUserByIdFallBack")
    public String queryUserById(@PathVariable("id") Long id) {
//        ServiceInstance instance = discoveryClient.getInstances("service-provider").get(0);
//        String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;


        // 超时熔断
//        try {
//            Thread.sleep(7000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        if (id == 2) {
//            throw new RuntimeException("太忙了");
//        }
//        String baseUrl = "http://service-provider/user/" + id;
//
//        User user = this.restTemplate.getForObject(baseUrl, User.class);
        User user = userClient.queryById(id);
        return user.toString();
    }

    public String queryUserByIdFallBack(Long id) {
        return "请求繁忙，请稍后再试！";
    }
}
