package com.ruan.polestar.controller;

import com.ruan.polestar.annotation.Ruan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
@Slf4j
public class LogCtrl {

    @RequestMapping("/test")
    public void testAop(){
        log.info("test LOG AOP");
    }
}
