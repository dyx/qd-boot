package com.lhd.qd.constant.http;

import lombok.Data;

/**
 * 接口返回结果
 * @author lhd
 */
@Data
public class ApiResult<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    private ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /***成功***/

    public static <T> ApiResult<T> success() {
        return getInstance(ApiCodeEnum.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return getInstance(ApiCodeEnum.SUCCESS, data);
    }

    /***失败***/

    public static <T> ApiResult<T> fail(String msg) {
        return getInstance(ApiCodeEnum.FAIL.getCode(), msg, null);
    }

    /***异常***/

    public static <T> ApiResult<T> error() {
        return getInstance(ApiCodeEnum.ERROR, null);
    }

    public static <T> ApiResult<T> error(Integer code) {
        return getInstance(code, ApiCodeEnum.ERROR.getMsg(), null);
    }

    public static <T> ApiResult<T> error(ErrorCodeEnum codeEnum) {
        return getInstance(codeEnum.getCode(), ApiCodeEnum.ERROR.getMsg(), null);
    }

    /***基础***/

    private static <T> ApiResult<T> getInstance(CodeEnum codeEnum, T data) {
        return getInstance(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    private static <T> ApiResult<T> getInstance(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }
}
