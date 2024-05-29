package com.xiaogong.service.modle;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-29
 */
@TableName(value = "sendSMS")
@Data
public class SendSMS implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @TableId(value = "id")
    private Long id;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "code")
    private String code;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "send_time")
    private LocalDateTime sendTime;
}
