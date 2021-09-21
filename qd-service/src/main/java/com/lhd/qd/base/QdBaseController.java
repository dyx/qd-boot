package com.lhd.qd.base;

import com.lhd.qd.constant.http.ApiResult;

/**
 * 基础控制器
 * @author lhd
 */
public class QdBaseController {

    protected static ApiResult<?> success() {
        return ApiResult.success();
    }

    protected static <T> ApiResult<T> success(T data) {
        return ApiResult.success(data);
    }
}
