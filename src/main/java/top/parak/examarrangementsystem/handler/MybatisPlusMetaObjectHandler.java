package top.parak.examarrangementsystem.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.handler </p>
 * <p> FileName: MybatisPlusMetaObjectHandler <p>
 * <p> Description: MybatisPlus填充策略 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * <p>插入时的填充策略</p>
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("gmtCreate", date, metaObject);
        this.setFieldValByName("gmtUpdate", date, metaObject);
    }

    /**
     * <p>更新时的填充策略</p>
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtUpdate", new Date(), metaObject);
    }
}
