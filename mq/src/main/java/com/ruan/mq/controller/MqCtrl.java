package com.ruan.mq.controller;

import com.ruan.mq.pojo.Trans;
import com.ruan.mq.producer.MerNoticeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mq")
@Slf4j
public class MqCtrl {

    @Autowired
    private MerNoticeSender noticeSender;

    @RequestMapping("/send")
    public void sendMQ(){
        Trans trans = new Trans("222","CASH");
        log.info("开始发送MQ消息");
        noticeSender.sendNoticeData(trans);
        log.info("MQ消息发送完成");
    }

}
