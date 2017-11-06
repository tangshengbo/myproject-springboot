package com.tangshengbo.service.impl;

import com.tangshengbo.service.TaskService;
import jodd.util.ThreadUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by Tangshengbo on 2017/11/6.
 */
@Service("taskAsyncService")
public class TaskAsyncServiceImpl implements TaskService {

    private static Random random = new Random();

    @Async
    public Future<String> doTaskOne() {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo()  {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThree()  {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
}
