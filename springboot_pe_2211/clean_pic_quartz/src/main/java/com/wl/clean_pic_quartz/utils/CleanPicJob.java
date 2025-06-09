package com.wl.clean_pic_quartz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Set;

import static com.wl.clean_pic_quartz.utils.RedisConstant.*;

@Component
public class CleanPicJob {



    //因为spring整合了redis，已经将redis的操作对象放到spring容器中了。只需要注入就可以使用
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //定义清理垃圾图片的任务
    @Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次任务
    private void task() {
        System.out.println("定时任务执行了..................");
        //需要操作的是set集合的对象
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        //获取到两个set集合的差集
        Set<String> difference = setOperations.difference( SET_MEAL_UPLOAD_PIC_SET_NAME,SET_MEAL_UPLOAD_DB_PIC_SET_NAME);

        System.out.println(difference + "...........................");
        //如果两个集合有差集，开始清理
        if (difference != null) {
            for (String pic : difference) {
                //本地文件删除掉
                new File(FILE_PATH, pic).delete();
                //redis中uploadPic集合中删除
                setOperations.remove(SET_MEAL_UPLOAD_PIC_SET_NAME,pic);
            }
        }

    }
}
