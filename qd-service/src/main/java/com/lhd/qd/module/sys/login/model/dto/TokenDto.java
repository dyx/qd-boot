package com.lhd.qd.module.sys.login.model.dto;

import lombok.Data;

/**
 * 凭证传输对象
 * @author lhd
 */
@Data
public class TokenDto {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 客户端id
     */
    private String clientId;

    public TokenDto() {

    }

    public TokenDto(Long userId, String clientId) {
        this.userId = userId;
        this.clientId = clientId;
    }
}
