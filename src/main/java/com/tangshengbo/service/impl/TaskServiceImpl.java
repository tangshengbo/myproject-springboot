package com.tangshengbo.service.impl;

import com.tangshengbo.service.TaskService;
import jodd.util.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Tangshengbo on 2017/11/6.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    private static Random random = new Random();

    public String doTaskOne() {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return "";
    }

    public String doTaskTwo()  {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return "";
    }

    public String doTaskThree()  {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        ThreadUtil.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return "";
    }
}


