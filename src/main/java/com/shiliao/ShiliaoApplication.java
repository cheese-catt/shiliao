package com.shiliao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.shiliao.mapper")
public class ShiliaoApplication {
    public static void main(String[] args) {

        SpringApplication.run(ShiliaoApplication.class,args);
    }
}
