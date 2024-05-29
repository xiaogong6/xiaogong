package com.xiaogong.string;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-07
 */
public class EqualsTest {

    private final Logger logger = LoggerFactory.getLogger(EqualsTest.class);

    @Test
    public void test1(){
        String a = "a";
        String b = "a";
        String c = new String("a");
        String d = new String("a");
        logger.info("a==b:{}",a==b);
        logger.info("a.equals(b):{}",a.equals(b));

        logger.info("c==d:{}",c==d);
        logger.info("c.equals(d):{}",c.equals(d));
    }

}
