package com.lhd.qd.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.DigestUtils;

/**
 * 安全工具
 * @author lhd
 */
public class SecurityUtils {

    public static String md5(String plaintext) {
        if (StrUtil.isEmpty(plaintext)) {
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
}
