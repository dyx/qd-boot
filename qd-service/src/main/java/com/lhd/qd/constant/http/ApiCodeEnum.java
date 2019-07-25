package com.lhd.qd.constant.http;

/**
 * 接口响应编码
 * @author lhd
 */
public enum ApiCodeEnum implements CodeEnum {

    /**
     * 成功，值为>0，如保存成功
     * 此类信息，直接显示给用户
     */
    SUCCESS(1, "成功"),
    /**
     * 失败，值为0，如参数校验未通过
     * 此类信息，直接显示给用户
     */
    FAIL(0, "失败"),
    /**
     * 异常，值为<0，如运行时异常
     * 此类信息，隐藏细节信息并记录在日志文件中
     * 显示给用户异常编码和模糊的提示信息
     */
    ERROR(-1, "服务器异常，请联系管理员");

    private Integer code;
    private String msg;

    ApiCodeEnum(Integer code, String desc) {
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
