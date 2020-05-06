package com.ruan.polestar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/async")
@Controller
@Slf4j
public class AsyncCtrl {

    @RequestMapping("/test")
    @Async
    public void testAsync() throws Exception {
        log.info("Test Async");
        int i = Integer.parseInt("xxxd");
        Thread.sleep(2000);
        log.info("OVER");
    }
}
