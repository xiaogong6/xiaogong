package com.xiaogong.leetcode.top150.day01;

/**
 * @Program: xiaogong
 * @Description: 移除元素
 * @Author: xiongke
 * @Create: 2024-06-26
 */
public class LeetCode27 {

    public static void main(String[] args) {
        removeElement(new int[]{3,2,2,3},3);
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
     * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
     * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
     * 返回 k。
     * @param nums 目标数组
     * @param val 移除值
     * @return 剩余数组size
     */
    private static int removeElement(int[] nums, int val) {
        int res = 0;
        for (int num : nums) {
            if(num!=val){
                nums[res]=num;
                res++;
            }
        }
        return res;
    }
}
