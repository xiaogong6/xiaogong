package com.xiaogong;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class DemoAggregateApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.of(1988, 6, 1, 0, 0, 0);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        System.out.println("--------------------------------------------");
        LocalDateTime parse = LocalDateTime.ofInstant(Instant.ofEpochMilli(581094000000L),ZoneId.systemDefault());
        System.out.println(parse);
    }

    /**
     * 1、
     * 在中国，夏令时曾经在1986年至1991年期间实行。
     * 具体做法是每年从四月中旬的第一个星期日凌晨2时（北京时间）开始，将时钟拨快一小时，到每年的九月中旬的第一个星期日凌晨2时（北京夏令时间）再将时钟拨回一小时。
     * 但需要注意的是，1986年是实行夏令时的第一年，是从5月4日开始到9月14日结束，其他年份均按规定的时段施行。然而，由于多种原因，中国在1992年暂停了夏令时的实行。
     * 2、
     * 小程序注册会员时填写的会员生日，传入1988年6月1日保存时为1988年6月1日 00:00:00
     * 接收参数类型为java.time.LocalDateTime,对夏令时做过特殊处理
     * 按照夏令时 1988年6月1日 00:00:00 真实生日为1988年5月30日23:00:00 将LocalDateTime转成毫秒值存入数据库中时会保存真实生日时间
     * 3、
     * job任务查询6月份的会员时,Month()函数匹配该月的生日会员却查询不到,导致遗漏 1667232000000
     * 后台用会员手机号查询会员详情时会发现会员的生日又显示为1988年6月1日 结果用LocalDateTime类型接收 可以将会员查询出来 产生歧义
     * 4、
     *MONTH(DATE_ADD(FROM_UNIXTIME(3600),INTERVAL birthday/1000 SECOND)) 使用DATE_ADD()函数加一个小时 解决问题
     * 5、
     * MySQL函数索引 mysql8之后的索引优化技术 函数索引不是直接在表的列上创建的，而是基于列的某个表达式创建的
     *
     */

}
