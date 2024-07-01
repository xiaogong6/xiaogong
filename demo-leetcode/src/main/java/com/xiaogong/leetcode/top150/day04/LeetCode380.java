package com.xiaogong.leetcode.top150.day04;

import java.util.*;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-07-01
 */
public class LeetCode380 {

    List<Integer> list;
    Map<Integer,Integer> map;
    Random random;

    public LeetCode380() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        int index = list.size();
        list.add(val);
        // val在list的下标
        map.put(val,index);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        int index = map.get(val);
        Integer last = list.get(list.size() - 1);
        // 最后一个元素替换到删除的位置
        list.set(index,last);
        // 更新最后一个元素的下标
        map.put(last,index);
        // 删除最后一个元素
        map.remove(list.size() - 1);
        list.remove(val);
        return true;
    }

    public int getRandom() {
        int i = random.nextInt(list.size());
        return list.get(i);
    }

}
