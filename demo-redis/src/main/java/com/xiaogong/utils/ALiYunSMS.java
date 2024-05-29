package com.xiaogong.utils;


import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-29
 */
public class ALiYunSMS {

    public static void main(String[] args) {
    }

    public static Boolean sendSMSByALiYun(String code, String phone) {
        String host = "https://wwsms.market.alicloudapi.com";
        String path = "/send_sms";
        String method = "POST";
        String appcode = "1d8e83e1d5b44fa9a88c3dadb2973b02";
        Map<String, String> headers = new HashMap<>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        // 根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", code);
        bodys.put("template_id", "CST_11253");
        bodys.put("phone_number", phone);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            // 获取response的body
            // System.out.println(EntityUtils.toString(response.getEntity()));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
