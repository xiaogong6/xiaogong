package com.xiaogong.leetcode.top150.day02;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: xiaogong
 * @Description: 多数元素
 * @Author: xiongke
 * @Create: 2024-06-27
 */
public class LeetCode169 {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * @param nums 目标数组
     * @return 出现次数>n/2的元素个数
     */
    public static int majorityElement(int[] nums) {
        int count = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer compute = map.compute(num, (k, v) -> v == null ? 1 : v + 1);
            if (compute > count) {
                return num;
            }
        }
        return 1;
    }

    /**
     * 投票算法
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
