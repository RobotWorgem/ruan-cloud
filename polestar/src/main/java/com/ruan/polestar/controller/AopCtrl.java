package com.ruan.polestar.controller;

import com.ruan.polestar.annotation.Ruan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Key;

@Controller
@RequestMapping("/aop")
@Slf4j
public class AopCtrl {

    @Ruan(key = "key",desc = "desc",result = String.class)
    @RequestMapping("/test")
    public void testAop(String name, String password, int age){
        log.info("test AOP");
    }
}
