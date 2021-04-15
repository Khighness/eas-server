package top.parak.examarrangementsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.parak.examarrangementsystem.util.IPParseUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.aspect </p>
 * <p> FileName: LogAspect <p>
 * <p> Description: 日志切面 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>定义切面表达式</p>
     */
    @Pointcut("execution(* top.parak.examarrangementsystem.controller.*.*(..))")
    public void log() {}

    /**
     * <p>前置操作</p>
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        String url = httpServletRequest.getRequestURL().toString();
        String ip = IPParseUtils.getIPAddress(httpServletRequest);

        /* 获取类名、方法名 */
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        /* 获取方法参数 */
        Object[] args = joinPoint.getArgs();

        ClientRequest requestLog = new ClientRequest(url, ip, classMethod, args);
        logger.info("{}", requestLog);
    }

    /**
     * <p>后置操作</p>
     */
    @After("log()")
    public void doAfter() { }

    /**
     * <p>输出响应</p>
     * @param response
     */
    @AfterReturning(returning = "response", pointcut = "log()")
    public void result(Object response) {
        logger.info("{}", response);
    }

}
