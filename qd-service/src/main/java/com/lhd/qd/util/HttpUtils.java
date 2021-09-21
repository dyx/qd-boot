package com.lhd.qd.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.lhd.qd.constant.CommonConsts;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * http工具
 * @author lhd
 */
public class HttpUtils {

    public static void setResponseBody(HttpServletResponse response, String msg) {
        if (StrUtil.isNotEmpty(msg)) {
            try {
                response.getOutputStream().write(msg.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取请求对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        catch (Exception e) {}
        return null;
    }

    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        String token = getRequest().getHeader(CommonConsts.REQUEST_HEADER_KEY_AUTH);
        return token;
    }

    /**
     * 获取浏览器信息
     * @return
     */
    public static String getOsBrowser() {

        HttpServletRequest request = getRequest();
        if (request != null) {
            UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
            return String.format("%s %s:%s %s:%s %s:%s",
                    userAgent.getPlatform(),
                    userAgent.getOs(), userAgent.getOsVersion(),
                    userAgent.getBrowser(), userAgent.getVersion(),
                    userAgent.getEngine(), userAgent.getEngineVersion());
        }

        return "";
    }

    /**
     * 获取请求的IP
     * @return
     */
    public static String getIp() {

        HttpServletRequest request = getRequest();
        if (request != null) {
            return ServletUtil.getClientIP(request);
        }
        return "";
    }
}
