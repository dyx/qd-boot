package com.lhd.qd.util;

import java.util.UUID;

/**
 * 生成UUID
 * @author lhd
 */
public class UuidUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
