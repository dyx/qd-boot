package com.lhd.qd.constant.http;

/**
 * 异常编码
 * @author lhd
 */
public enum  ErrorCodeEnum implements CodeEnum {

    /**
     * 起始值-1000
     * 每1000个为一类
     * 命名：类别_具体描述
     */
    HTTP_REQUEST_MISSING_PARAM(-2002, "请求缺失参数"),
    HTTP_REQUEST_NOT_SUPPORT_MEDIA_TYPE(-2001, "不支持的媒体类型"),

    AUTHORIZATION_ERROR(-1300, "鉴权异常"),

    JWT_PARSE_ERROR(-1201, "解析token异常"),
    JWT_VERIFY_ERROR(-1200, "校验token异常"),

    REDIS_EMPTY_KEY(-1100, "键不能为空"),

    DB_DUPLICATE_KEY(-1000, "主键冲突");

    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.msg = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
