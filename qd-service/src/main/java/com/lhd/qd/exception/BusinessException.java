package com.lhd.qd.exception;

import com.lhd.qd.constant.http.ApiCodeEnum;
import com.lhd.qd.constant.http.ErrorCodeEnum;
import lombok.Data;

/**
 * 业务异常
 * @author lhd
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String msg;

    /**
     * 具体信息
     */
    private Throwable detail;

    public BusinessException(String msg) {
        this(ApiCodeEnum.FAIL.getCode(),msg, null);
    }

    public BusinessException(String msg, Throwable detail) {
        this(ApiCodeEnum.FAIL.getCode(),msg, detail);
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMsg(), null);
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, Throwable detail) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMsg(), detail);
    }

    public BusinessException(Integer code, String msg, Throwable detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

}
