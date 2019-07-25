package com.lhd.qd.aspect;

import com.lhd.qd.util.HttpUtils;
import com.lhd.qd.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求日志记录
 *
 * @author lhd
 */
@Slf4j
@Aspect
@Component
public class HttpLogAop {

    private ThreadLocal<Long> initRequestTime = new ThreadLocal<>();

    @Value("${qd.log.http}")
    private Boolean isOpen;

    @Pointcut("execution(public * com.lhd.qd.module..controller.*Controller.*(..))")
    private void point() {
    }

    @Before("point()")
    public void before(JoinPoint joinPoint) {

        if (isOpen) {
            try {
                initRequestTime.set(System.currentTimeMillis());

                HttpServletRequest request = HttpUtils.getRequest();

                log.info("********** HTTP Request **********");
                log.info("url：{}", request.getRequestURI());
                log.info("method：{}", request.getMethod());
                log.info("classMethod：{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
                log.info("params：{}", JacksonUtils.toStr(joinPoint.getArgs()));

            } catch (Exception e) {
                log.error("记录Http请求日志出错", e);
            }
        }
    }

    @AfterReturning(returning = "obj", pointcut = "point()")
    public void afterReturning(Object obj) {

        if (isOpen) {
            try {
                HttpServletRequest request = HttpUtils.getRequest();
                log.info("********** HTTP Response **********");
                log.info("url：{}", request.getRequestURI());
                log.info("method：{}", request.getMethod());
                log.info("body：{}", JacksonUtils.toStr(obj));
                log.info("耗时：{}ms", (System.currentTimeMillis() - initRequestTime.get()));
                // 防止线程复用造成的数据混乱
                initRequestTime.remove();
            } catch (Exception e) {
                log.error("记录Http响应日志出错", e);
            }
        }
    }
}
