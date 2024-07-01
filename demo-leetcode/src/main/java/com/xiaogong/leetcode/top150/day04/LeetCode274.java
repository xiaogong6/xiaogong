package com.xiaogong.leetcode.top150.day04;

import java.util.Arrays;

/**
 * @Program: xiaogong
 * @Description: H 指数
 * @Author: xiongke
 * @Create: 2024-07-01
 */
public class LeetCode274 {

    public static void main(String[] args) {
        LeetCode274 leetCode274 = new LeetCode274();
        int[] citations = {3, 0, 6, 1, 5};
        // 0,1,3,5,6
        System.out.println(leetCode274.hIndex(citations));
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        for (int i = citations.length-1; i >=0 ; i--) {
            int num = citations[i];
            if (num<=res){
                continue;
            }
            res++;
        }
        return res;
    }

}
