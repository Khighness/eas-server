package top.parak.examarrangementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.parak.examarrangementsystem.interceptor.JWTInterceptor;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: InterceptorConfig <p>
 * <p> Description: 拦截器配置 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Configuration
public class InterceptorConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 添加JWT拦截器 */
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/api/**/**")
                /* 不拦截登录接口 */
                .excludePathPatterns("/api/login")
                /* 不拦截考务人员注册 */
                .excludePathPatterns("/api/examPlaceInvigilator/verify")
                .excludePathPatterns("/api/examPlaceInvigilator/register")
                /* 不拦截文件下载 */
                .excludePathPatterns("/api/file/download/**");
    }

}
