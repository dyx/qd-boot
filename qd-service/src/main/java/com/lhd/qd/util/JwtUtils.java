package com.lhd.qd.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lhd.qd.constant.http.ErrorCodeEnum;
import com.lhd.qd.exception.BusinessException;
import com.lhd.qd.module.sys.login.model.dto.TokenDTO;

import java.util.Date;

/**
 * token工具
 *
 * @author lhd
 */
public class JwtUtils {

    private final static String CLAIM_KEY_ID = "id";
    private final static String CLAIM_KEY_CLIENT_ID = "cid";


    /**
     * 生成token
     *
     * @param tokenDTO
     * @param secret
     * @param expiresDate
     * @return
     */
    public static String generate(TokenDTO tokenDTO, String secret, Date expiresDate) {
        return JWT.create()
                .withClaim(CLAIM_KEY_ID, tokenDTO.getUserId())
                .withClaim(CLAIM_KEY_CLIENT_ID, tokenDTO.getClientId())
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 获取token信息
     *
     * @param token
     * @return
     */
    public static TokenDTO parse(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new TokenDTO(jwt.getClaim(CLAIM_KEY_ID).asLong(), jwt.getClaim(CLAIM_KEY_CLIENT_ID).asString());
        } catch (Exception e) {
            throw new BusinessException(ErrorCodeEnum.JWT_PARSE_ERROR);
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @param secret
     */
    public static Boolean verify(String token, String secret) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
