package com.xiaogong.leetcode.top150.day01;

import java.util.Arrays;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-06-26
 */
public class LeetCode88 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3, n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
     * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * @param nums1 数组1
     * @param m     数组1的size
     * @param nums2 数组2
     * @param n     数组2的size
     */
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            int nums1Max = nums1[i];
            int nums2Max = nums2[j];
            if (nums2Max >= nums1Max) {
                nums1[k] = nums2Max;
                j--;
            } else {
                nums1[k] = nums1Max;
                i--;
            }
            k--;
        }
        // 对于nums1为空特殊处理
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

}
