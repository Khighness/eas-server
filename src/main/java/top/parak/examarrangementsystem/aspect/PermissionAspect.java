package top.parak.examarrangementsystem.aspect;

import com.auth0.jwt.JWT;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.parak.examarrangementsystem.annotation.Permission;
import top.parak.examarrangementsystem.exception.InsufficientAuthorityException;

/**
 * <p> Project: examArrangementSystem </P>
 * <p> Package: top.parak.examarrangementsystem.annotation </p>
 * <p> FileName: PermissionLevel <p>
 * <p> Description: 接口权限级别 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/4/20
 */

@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(top.parak.examarrangementsystem.annotation.Permission)")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 获取角色ID
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = attributes.getRequest().getHeader("Authorization");
        Integer roleId = JWT.decode(token).getClaim("roleId").asInt();
        // 获取权限level
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Integer level = methodSignature.getMethod().getAnnotation(Permission.class).level().getLevel();
        if (roleId > level) {
            throw new InsufficientAuthorityException();
        }
    }
}
