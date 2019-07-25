package com.lhd.qd.util;

import com.lhd.qd.constant.CommonConsts;
import com.lhd.qd.constant.RedisConsts;
import com.lhd.qd.constant.dict.PageElementMethodEnum;
import com.lhd.qd.module.sys.user.model.converter.AbstractUserConverter;
import com.lhd.qd.module.sys.user.model.vo.UserCacheVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 用户工具
 * @author lhd
 */
public class UserUtils {

    public static Long getUserId() {
        return JwtUtils.parse(HttpUtils.getToken()).getUserId();
    }

    public static String getClientId() {
        return JwtUtils.parse(HttpUtils.getToken()).getClientId();
    }

    public static UserCacheVO getCurrentUser() {

        RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);
        UserCacheVO vo = AbstractUserConverter.INSTANCE.map2CacheVO(redisUtils.getHash(RedisConsts.getUserKey(getUserId())));

        return vo;
    }

    public static Boolean hasPermission(String method, String url) {

        if (StringUtils.isEmpty(method) || StringUtils.isEmpty(url)) {
            return false;
        }

        List<String> permissionList = getCurrentUser().getPermissionList();
        if (permissionList == null || permissionList.size() == 0) {
            return false;
        }

        method = method.trim().toUpperCase();
        url = handleUrl(url);

        String parentPermission = "";
        if (PageElementMethodEnum.GET.getDesc().equals(method)) {

            /**
             * 获取上级权限
             * 有GET:/user的权限则有GET:/user/**权限
             */
            int secondSeparatorIndex = url.indexOf(CommonConsts.URL_SEPARATOR, 1);
            if (secondSeparatorIndex > 0) {
                parentPermission = method + CommonConsts.PERMISSION_SEPARATOR + url.substring(0, secondSeparatorIndex);
            }
        }

        // 校验权限
        String currentPermission = method + CommonConsts.PERMISSION_SEPARATOR + url;
        for (String permission: permissionList) {

            String regex = "^" + permission.replaceAll("\\{.*?\\}", "[a-zA-Z\\\\d]+") + "$";
            if (StringUtils.isNotEmpty(permission)) {

                if (permission.equals(parentPermission)
                        || Pattern.compile(regex).matcher(currentPermission).find()) {
                    return true;
                }
            }
        }

        return false;
    }

    private static String handleUrl(String url) {

        String currentUrl = url.trim();
        if (!currentUrl.startsWith(CommonConsts.URL_SEPARATOR)) {
            currentUrl = CommonConsts.URL_SEPARATOR + url;
        }
        if (currentUrl.endsWith(CommonConsts.URL_SEPARATOR)) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - CommonConsts.URL_SEPARATOR.length());
        }

        return currentUrl;
    }

}
