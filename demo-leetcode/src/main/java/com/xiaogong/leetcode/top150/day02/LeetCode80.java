package com.xiaogong.leetcode.top150.day02;

import java.util.Arrays;

/**
 * @Program: xiaogong
 * @Description: 删除有序数组中的重复项Ⅱ
 * @Author: xiongke
 * @Create: 2024-06-27
 */
public class LeetCode80 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums2));
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums 目标数组
     * @return 数组size
     */
    public static int removeDuplicates(int[] nums) {
        int p = 2, q = 2;
        while (q < nums.length) {
            if (nums[p-2] != nums[q]) {
                nums[p] = nums[q];
                p++;
            }
            q++;
        }
        System.out.println(Arrays.toString(nums));
        return p ;
    }

}
