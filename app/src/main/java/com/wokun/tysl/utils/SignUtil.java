package com.wokun.tysl.utils;

import com.shantoo.common.utils.MD5Util;
import com.wokun.tysl.config.Constants;

public class SignUtil {

    /**
     * sign算法：md5加密拼接字符串，字符串有以下几个参数连接
     * （请求地址、秘钥、用户id、access_token、当前时间戳）
     * @param url String
     * @param accessToken String
     * @param timeStamp long
     */
    public static String getSign(String url, String accessToken, long timeStamp) {
        String sign = url + Constants.KEY + accessToken + timeStamp;
        return MD5Util.encrypt(sign);
    }

    /**
     * sign算法：md5加密拼接字符串，字符串有以下几个参数连接
     * （请求地址、秘钥、用户id、access_token、当前时间戳）
     * @param url String
     * @param userId String
     * @param accessToken String
     * @param timeStamp long
     */
    public static String getSign(String url, String userId, String accessToken, long timeStamp) {
        String sign = url + Constants.KEY + userId + accessToken + timeStamp;
        return MD5Util.encrypt(sign);
    }
}