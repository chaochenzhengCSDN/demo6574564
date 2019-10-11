package com.zcc.redis123;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.zcc.redis123.dao")
public class Redis123Application {
    public static void main(String[] args) {
        SpringApplication.run(Redis123Application.class, args);

    }

}
