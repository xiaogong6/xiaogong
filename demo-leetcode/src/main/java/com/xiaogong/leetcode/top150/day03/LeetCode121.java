package com.xiaogong.leetcode.top150.day03;

/**
 * @Program: xiaogong
 * @Description: 买卖股票的最佳时机
 * @Author: xiongke
 * @Create: 2024-06-28
 */
public class LeetCode121 {

    public static void main(String[] args) {
        LeetCode121 leetCode121 = new LeetCode121();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(leetCode121.maxProfit(prices));
    }


    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * @param prices 数组
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices== null|| prices.length == 0){
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < minPrice){
                minPrice = prices[i];
                continue;
            }
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

}
