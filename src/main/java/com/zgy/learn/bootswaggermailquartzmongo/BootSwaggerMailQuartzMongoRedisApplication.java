package com.zgy.learn.bootswaggermailquartzmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication()
public class BootSwaggerMailQuartzMongoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSwaggerMailQuartzMongoRedisApplication.class, args);
    }

}
