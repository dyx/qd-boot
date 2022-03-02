package com.lhd.qd.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.lhd.qd.constant.http.ErrorCodeEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;

/**
 * 鉴权
 */
@Slf4j
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(com.lhd.qd.annotation.Permission)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        String permissionCode = getPermissionCode(pjp);
        log.debug("权限编码:{}", permissionCode);
        if (!CollUtil.contains(UserUtils.getCurrentUser().getPermissionList(), permissionCode)) {
            throw new BusinessException("无权访问");
        }

        return pjp.proceed();
    }

    /**
     * 获取权限Code
     *
     * 类注解必须为RequestMapping
     *     path的值，只能有一个
     *
     * 方法注解RequestMapping
     *     Method的值，有且只有一个
     *     path的值，只能有一个
     *
     * 方法注解GetMapping、PostMapping、PutMapping、DeleteMapping
     *     path的值，只能有一个
     *
     * 权限Code格式
     *     GET:/user
     *     GET:/user/{id}
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    private String getPermissionCode(ProceedingJoinPoint pjp) throws NoSuchMethodException {

        Class<?> targetClass = pjp.getTarget().getClass();
        RequestMapping classAnnotation = targetClass.getAnnotation(RequestMapping.class);
        String rootUri = classAnnotation == null ? null : getUri(classAnnotation.value(), classAnnotation.path());

        MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());
        Annotation[] methodAnnotations = targetClass
                .getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes())
                .getAnnotations();
        RequestMethod requestMethod = null;
        String uri = null;
        for (Annotation annotation : methodAnnotations) {

            if (annotation instanceof RequestMapping){

                uri = getUri(((RequestMapping) annotation).value(), ((RequestMapping) annotation).path());
                requestMethod = getRequestMethod(((RequestMapping) annotation).method());
                break;
            }
            else if (annotation instanceof PostMapping) {

                uri = getUri(((PostMapping) annotation).value(), ((PostMapping) annotation).path());
                requestMethod = RequestMethod.POST;
                break;
            }
            else if (annotation instanceof PutMapping) {

                uri = getUri(((PutMapping) annotation).value(), ((PutMapping) annotation).path());
                requestMethod = RequestMethod.PUT;
                break;
            }
            else if (annotation instanceof DeleteMapping) {

                uri = getUri(((DeleteMapping) annotation).value(), ((DeleteMapping) annotation).path());
                requestMethod = RequestMethod.DELETE;
                break;
            }
            else if (annotation instanceof GetMapping) {

                uri = getUri(((GetMapping) annotation).value(), ((GetMapping) annotation).path());
                requestMethod = RequestMethod.GET;
                break;
            }
        }

        if (requestMethod == null) {
            return "";
        }

        return String.format("%s:%s", requestMethod.toString(), getFinalUri(rootUri, uri));
    }

    private RequestMethod getRequestMethod(RequestMethod[] requestMethods) {
        if (requestMethods.length == 1) {
            return requestMethods[0];
        }
        return null;
    }

    private String getUri(String[] values, String[] paths) {

        if (values.length > 1 || paths.length > 1) {
            throw new BusinessException("接口URI设置错误");
        }

        if (values.length == 0) {

           return paths.length == 0 ? null : paths[0];
        }

        return values[0];
    }

    private static String getFinalUri(String rootUri, String uri) {

        rootUri = removePrefixAndSuffix(rootUri);
        uri = removePrefixAndSuffix(uri);

        if (StrUtil.isEmpty(rootUri) && StrUtil.isEmpty(uri)) {
            return "";
        }

        String finalUrl;
        if (StrUtil.isEmpty(rootUri)) {
            finalUrl = String.format("/%s", uri);
        }
        else if (StrUtil.isEmpty(uri)) {
            finalUrl = String.format("/%s", rootUri);
        }
        else {
            finalUrl = String.format("/%s/%s", rootUri, uri);
        }

        return finalUrl;
    }

    private static String removePrefixAndSuffix(String uri) {

        return removeSuffix(removePrefix(uri));
    }

    private static String removePrefix(String uri) {

        if (StrUtil.isEmpty(uri)) {
            return uri;
        }

        if (uri.startsWith("/")) {
            return uri.substring(1);
        }

        return uri;
    }

    private static String removeSuffix(String uri) {

        if (StrUtil.isEmpty(uri)) {
            return uri;
        }

        if (uri.endsWith("/")) {
            return uri.substring(0, uri.length() - 1);
        }

        return uri;
    }
}
