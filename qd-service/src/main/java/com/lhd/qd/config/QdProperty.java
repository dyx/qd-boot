package com.lhd.qd.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义配置文件属性
 * @author lhd
 */
@Setter
@Getter
@ToString
@Component
public class QdProperty {

    @Value("${qd.log.http}")
    private Boolean httpLog;

    @Value("${qd.security.salt-times}")
    private Integer saltTimes;

    @Value("${qd.token.expires-minutes}")
    private Integer tokenExpiresMinutes;

    @Value("${qd.user.init-pwd}")
    private String userInitPwd;

    @Value("${qd.user.ignore-url}")
    private List<String> userIgnoreUrlList;
}
