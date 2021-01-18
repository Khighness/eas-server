package top.parak.examarrangementsystem.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: MybatisPlusConfigure <p>
 * <p> Description: MybatisPlus配置 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Configuration
public class MybatisPlusConfigure {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()  {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        /* 分页插件 */
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        /* 乐观锁插件 */
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }
    
}
