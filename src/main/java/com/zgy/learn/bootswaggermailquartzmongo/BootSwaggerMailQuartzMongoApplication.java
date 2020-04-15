package com.zgy.learn.bootswaggermailquartzmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication()
public class BootSwaggerMailQuartzMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSwaggerMailQuartzMongoApplication.class, args);
    }

}
