package com.wl.springboot_pe_2211;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper层的bean
@MapperScan("com.wl.springboot_pe_2211.mapper")
public class SpringbootPe2211Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPe2211Application.class, args);
    }

}
