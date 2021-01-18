package top.parak.examarrangementsystem.annotation;

import top.parak.examarrangementsystem.annotation.impl.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> Project: examArrangementSystem </P>
 * <p> Package: top.parak.examarrangementsystem.annotation </p>
 * <p> FileName: Email <p>
 * <p> Description: 邮箱校验 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = EmailValidator.class
)
public @interface Email {
    String message() default "邮箱格式非法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
