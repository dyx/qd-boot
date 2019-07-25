package com.lhd.qd.util;

import com.lhd.qd.constant.CommonConsts;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
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

    private static final String SEPARATOR = ",";
    private static final String UNKNOWN = "unknown";

    public static void setResponseBody(HttpServletResponse response, String msg) {
        if (StringUtils.isNotEmpty(msg)) {
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
    public static String getOSBrowser() {

        return getOSBrowser(getRequest());
    }

    /**
     * 获取浏览器信息
     * @param request
     * @return
     */
    public static String getOSBrowser(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String agentStr = request.getHeader("User-Agent");
        if (StringUtils.isEmpty(agentStr)) {
            return "";
        }

        UserAgent userAgent = UserAgent.parseUserAgentString(agentStr);
        return userAgent.toString();
    }

    /**
     * 获取请求的IP
     * @return
     */
    public static String getIP() {
        return getIP(getRequest());
    }

    /**
     * 获取请求的IP
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(SEPARATOR)) {
                ip = ip.split(SEPARATOR)[0];
            }
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
