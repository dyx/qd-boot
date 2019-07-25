package com.lhd.qd.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 安全工具
 * @author lhd
 */
public class SecurityUtils {


    public static String md5(String plaintext) {
        if (StringUtils.isEmpty(plaintext)) {
            return "";
        }
        return DigestUtils.md5DigestAsHex(plaintext.getBytes());
    }

    public static String md5WithSalt(String plaintext, String salt, int times) {

        times = Math.max(1, times);
        times = Math.min(256, times);

        String ciphertext = plaintext + salt;
        for(int i = 0; i < times; ++i) {
            ciphertext = md5(ciphertext);
        }

        return ciphertext;
    }

    public static void main(String[] args) {

        String salt = "abcdefg";
        String pwd = "123456";
        int saltTimes = 128;

        System.out.println("1:" + md5WithSalt("", "", saltTimes));
        System.out.println("2:" + md5WithSalt(pwd, "", saltTimes));
        System.out.println("3:" + md5WithSalt("", salt, saltTimes));
        System.out.println("4:" + md5WithSalt(pwd, salt, saltTimes));
    }
}
