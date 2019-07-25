package com.lhd.qd.module.sys.login.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 凭证传输对象
 * @author lhd
 */
@Setter
@Getter
@ToString
public class TokenDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 客户端id
     */
    private String clientId;

    public TokenDTO() {

    }

    public TokenDTO(Long userId, String clientId) {
        this.userId = userId;
        this.clientId = clientId;
    }
}
