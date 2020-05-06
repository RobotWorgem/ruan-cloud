package com.ruan.log.annotation;

import com.ruan.log.config.LogConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({LogConfig.class})
@Documented
@Inherited
public @interface EnableLog {
}
