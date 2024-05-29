package com.xiaogong.arrayList;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-01
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = Maps.newHashMapWithExpectedSize(7);

        List<String> list = Arrays.asList("1", "2", "3");

        Stream<String> stream = list.stream();

        Stream<String> stringStream = list.parallelStream();

        Map<String, String> stringMap = Collections.synchronizedMap(hashMap);

        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();

        // stringStream.reduce()

        synchronized (hashMap){
            System.out.println("111");
        }
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        Object put = concurrentHashMap.put("1", "1");
        System.out.println("put = " + put);
        Object put1 = concurrentHashMap.put("1", "2");
        System.out.println("put1 = " + put1);
    }

}
