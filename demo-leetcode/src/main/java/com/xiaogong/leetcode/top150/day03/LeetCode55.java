package com.xiaogong.leetcode.top150.day03;

/**
 * @Program: xiaogong
 * @Description: 跳跃游戏
 * @Author: xiongke
 * @Create: 2024-06-28
 */
public class LeetCode55 {

    /**
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     * @param nums 数组
     * @return true/false
     */
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length-1; i++) {
            if (max<i){
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return max>= nums.length -1;
    }

    // 拓展右边界
    public boolean canJump2(int[] nums) {
        int right = nums[0];
        int n = nums.length;

        // 拓展右边界（当右边界不能继续拓展时，也会退出循环）
        for(int i = 0; i <= right; ++i){
            right = Math.max(right, nums[i] + i);
            if(right >= n - 1){
                // 右边界已经可以覆盖数组元素时，直接返回结果
                return true;
            }
        }
        return false;
    }

}
