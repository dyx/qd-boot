package com.lhd.qd.handler;

import com.lhd.qd.config.QdProperty;
import com.lhd.qd.constant.http.ApiResult;
import com.lhd.qd.constant.http.ErrorCodeEnum;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVo;
import com.lhd.qd.util.HttpUtils;
import com.lhd.qd.util.JacksonUtils;
import com.lhd.qd.util.JwtUtils;
import com.lhd.qd.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权过滤器
 *
 * @author lhd
 */
@Slf4j
@Component
@Order(1)
@WebFilter(urlPatterns = {"/*"}, filterName = "authorizationFilter")
public class QdAuthorizationFilter implements Filter {

    @Autowired
    private QdProperty qdProperty;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();

        if (!isIgnoreUrl(requestUri)) {

            try {

                UserCacheVo userCacheVo = UserUtils.getCurrentUser();
                // 校验token
                if (!JwtUtils.verify(HttpUtils.getToken(), userCacheVo.getSalt())) {
                    log.debug("鉴权URL：{}", requestUri);
                    response.sendError(HttpStatus.UNAUTHORIZED.value());
                    return;
                }
            }
            catch (Exception e) {
                log.error(ErrorCodeEnum.AUTHORIZATION_ERROR.getMsg(), e);
                HttpUtils.setResponseBody(response, JacksonUtils.toStr(ApiResult.error(ErrorCodeEnum.AUTHORIZATION_ERROR)));
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private Boolean isIgnoreUrl(String url) {
        for (String ignoreUrl : qdProperty.getUserIgnoreUrlList()) {
            if (isMatch(ignoreUrl, url)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isMatch(String ignoreUrl, String url) {

        Boolean isEqualMatch = ignoreUrl.equals(url);
        Boolean isLikeMatch = ignoreUrl.contains("/**") && url.startsWith(ignoreUrl.replace("/**", ""));

        return isEqualMatch || isLikeMatch;
    }
}
