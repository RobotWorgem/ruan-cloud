package com.ruan.mq;

import com.ruan.mq.banner.RuanBootBanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
//@EnableDiscoveryClient
@RestController
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MqApplication.class);
        application.setBanner(new RuanBootBanner());
        application.run(args);
    }

}
