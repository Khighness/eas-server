package top.parak.examarrangementsystem.annotation;

import top.parak.examarrangementsystem.common.PermissionType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    PermissionType level() default PermissionType.EXAM_PLACE_INVIGILATOR;
    String param() default "";
}
