package com.xiaogong.thread;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-15
 */
@Getter
public class WrongInit {

    private Map<Integer, String> students;

    public WrongInit() {

        new Thread(() -> {

            students = new HashMap<>();

            students.put(1, "王小美");

            students.put(2, "钱二宝");

            students.put(3, "周三");

            students.put(4, "赵四");

        }).start();

    }

    public static void main(String[] args) throws InterruptedException {

        WrongInit multiThreadsError = new WrongInit();

        // System.out.println(multiThreadsError.getStudents().get(1));

    }

}
