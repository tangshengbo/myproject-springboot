package com.tangshengbo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * Created by Tangshengbo on 2017/11/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class TaskServiceTest {

    @Qualifier("taskAsyncService")
    @Autowired
    private TaskService taskService;

    @Test
    public void testSync() throws Exception {
        long start = System.currentTimeMillis();
        taskService.doTaskOne();
        taskService.doTaskTwo();
        taskService.doTaskThree();
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void testAsync() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = taskService.doTaskOne();
        Future<String> task2 = taskService.doTaskTwo();
        Future<String> task3 = taskService.doTaskThree();
//        while(true) {
//            if(task1.isDone() && task2.isDone() && task3.isDone()) {
//                // 三个任务都调用完成，退出循环等待
//                break;
//            }
//            Thread.sleep(1000);
//        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
