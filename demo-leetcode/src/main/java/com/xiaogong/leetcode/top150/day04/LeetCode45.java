package com.xiaogong.leetcode.top150.day04;

/**
 * @Program: xiaogong
 * @Description: 跳跃游戏Ⅱ
 * @Author: xiongke
 * @Create: 2024-07-01
 */
public class LeetCode45 {

    public static void main(String[] args) {
        LeetCode45 leetCode45 = new LeetCode45();
        int[] nums = new int[]{2, 3, 1, 1, 4};
        // int[] nums = new int[]{0};
        System.out.println(leetCode45.jump2(nums));
    }


    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length==1){
            return 0;
        }
        int res = 0;
        int start = 0;
        int currMaxLen = nums[0];
        for (int i = 0; i < nums.length; i++) {
            // 当前最远距离
            currMaxLen = Math.max(currMaxLen, i + nums[i]);
            // 如果当前最远距离大于等于数组长度-1，说明可以跳到最后一个位置
            if(currMaxLen>= nums.length - 1){
                // 跳跃次数加一
                res++;
                break;
            }
            // 当前位置是否为当前最远距离
            if (i==start){
                // 跳跃次数加一
                res++;
                // 更新最远距离
                start = currMaxLen;
            }
        }
        return res;
    }

    public int jump2(int[] nums) {
        int res = 0;
        int start = 0;
        int currMaxLen = 0;
        for (int i = 0; i < nums.length-1; i++) {
            currMaxLen = Math.max(currMaxLen, i + nums[i]);
            if (i == start) {
                start = currMaxLen;
                res++;
            }
        }
        return res;
    }

}
