package com.lhd.qd.handler;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.lhd.qd.util.HttpUtils;

/**
 * 自定义日志输出信息
 * @author lhd
 */
public class QdClassicConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return String.format("%s-%s", HttpUtils.getIp(), HttpUtils.getOsBrowser());
    }
}
