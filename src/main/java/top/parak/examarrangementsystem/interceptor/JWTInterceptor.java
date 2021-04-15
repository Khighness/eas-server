package top.parak.examarrangementsystem.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.util.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.interceptor </p>
 * <p> FileName: JWTInterceptor <p>
 * <p> Description: JWT令牌拦截器<p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Slf4j
@CrossOrigin
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        Map<String, Object> map = new HashMap<>();
        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            log.info("Email => [{}]", decodedJWT.getClaim("email").asString());
            return true;
        } catch (TokenExpiredException e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", "令牌过期");
        } catch (SignatureVerificationException e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", "签名错误");
        } catch (AlgorithmMismatchException e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", "加密算法不匹配");
        } catch (InvalidClaimException e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", "失效负载");
        } catch (NullPointerException e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", "令牌为空");
        } catch (Exception e) {
            log.error("令牌解析异常 => [{}]", e.getMessage());
            map.put("message", e.getMessage());
        }
        map.put("code", HttpStatus.CLIENT_ERROR.getCode());
        JSONObject json = new JSONObject(map);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Max-Age","3600");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return false;
    }

}
