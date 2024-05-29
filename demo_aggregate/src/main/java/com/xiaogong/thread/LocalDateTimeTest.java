package com.xiaogong.thread;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-09
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(1988, 6, 1, 0, 0, 0);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

}
