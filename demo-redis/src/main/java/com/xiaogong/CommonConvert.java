package com.xiaogong;

import com.xiaogong.service.modle.SendSMS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-29
 */
@Mapper(imports = {java.time.LocalDateTime.class})
public interface CommonConvert {

    CommonConvert INSTANCE = Mappers.getMapper(CommonConvert.class);

    @Mapping(target = "status", constant = "1")
    @Mapping(target = "sendTime", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "verificationCode")
    @Mapping(target = "phone", source = "mobile")
    SendSMS converToSendSMS(String mobile, String type, String verificationCode);

}
