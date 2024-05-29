package com.xiaogong;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-29
 */
@Data
public class SmsSendRecord {

    private Long id;
    private String mobile;
    private String type;
    private String verificationCode;

    public SmsSendRecord(String mobile, String type, String verificationCode) {
        this.mobile = mobile;
        this.type = type;
        this.verificationCode = verificationCode;
        this.id = RandomUtil.randomLong(10000);
    }
}
