package com.xiaogong.enums;

import lombok.Getter;

@Getter
public enum RedisKeyEnum {

    //订单流水号 rediskey；按租户隔离  有效期暂不使用， 该流水号 订单、退款单、退货单共用
    ORDER_GENERATE_NUMBER("order:generatenumber:{0}",24*60),

    ;



    /**
     * redis的key
     */
    private final String key;

    /**
     * 过期时间(时间为分钟)
     */
    private final long expires;

    RedisKeyEnum(String key, long expires) {
        this.key = key;
        this.expires = expires;
    }
}
