package top.parak.examarrangementsystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: JWTUtils <p>
 * <p> Description: JWT工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 可以静态调用
 */

public class JWTUtils {

    /**
     * <p>签名的密钥[@Name]</p>
     */
    private static final String SECRET = "@YYY";

    /**
     * <p>签名的过期时间[A Week]</p>
     */
    private static final int ACCESS_TOKEN_EXPIRE_TIME = 604800;

    /**
     * <p>令牌颁布者身份标识[Domain]</p>
     */
    private static final String ISSUER = "parak.top";

    /**
     * <p>生成token</p>
     * @param chaims
     * @return
     */
    public static String generateToken(Map<String, String> chaims) {
        JWTCreator.Builder builder = JWT.create();
        /* 添加负载 */
        chaims.forEach((k, v) -> { builder.withClaim(k, v); });
        /* 设置过期时间 */
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_TIME);
        builder.withExpiresAt(calendar.getTime());
        /* 设置颁发者身份 */
        builder.withIssuer(ISSUER);
        /* 设置加密算法以及密钥 */
        String token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * <p>解析token</p>
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * <p>通过ID、email和roleID生成Token</p>
     * @param id
     * @param roleId
     * @return
     */
    public static String generateTokenById(Long id, String email, Integer roleId) {
        JWTCreator.Builder builder = JWT.create();
        /* 添加负载 */
        builder.withClaim("id", id);
        builder.withClaim("email", email);
        builder.withClaim("roleId", roleId);
        /* 设置过期时间 */
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, ACCESS_TOKEN_EXPIRE_TIME);
        builder.withExpiresAt(calendar.getTime());
        /* 设置颁发者身份 */
        builder.withIssuer(ISSUER);
        /* 设置加密算法以及密钥 */
        String token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }

}
