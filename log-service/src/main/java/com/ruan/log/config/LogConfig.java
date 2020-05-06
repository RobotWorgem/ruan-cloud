package com.ruan.log.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author XY
 */
@ComponentScan({"com.ruan.log"})
@EnableAspectJAutoProxy
public class LogConfig {

    public LogConfig() {
    }
}
