package com.wl.clean_pic_quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//定时任务激活
@EnableScheduling
public class CleanPicQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanPicQuartzApplication.class, args);
    }

}
