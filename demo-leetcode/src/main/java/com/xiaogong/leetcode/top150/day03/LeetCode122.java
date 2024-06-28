package com.xiaogong.leetcode.top150.day03;

/**
 * @Program: xiaogong
 * @Description: 买卖股票的最佳时机 II
 * @Author: xiongke
 * @Create: 2024-06-28
 */
public class LeetCode122 {

    public static void main(String[] args) {
        LeetCode122 leetCode122 = new LeetCode122();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices2 = new int[]{ 1,2,3,4, 5,6};
        System.out.println(leetCode122.maxProfit(prices2));
    }

    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * 可以买多次等价于每天都买(只买上涨日 与前一天比较)
     * 贪心算法：所有价格上涨日都购买股票,所有价格下降日都不购买
     * @param prices 数组
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int currProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i-1]) {
                continue;
            }
            currProfit += prices[i] - prices[i - 1];
        }
        return currProfit;
    }
}
