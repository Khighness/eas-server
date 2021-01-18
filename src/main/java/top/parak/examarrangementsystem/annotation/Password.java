package top.parak.examarrangementsystem.annotation;

import top.parak.examarrangementsystem.annotation.impl.EmailValidator;
import top.parak.examarrangementsystem.annotation.impl.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> Project: examArrangementSystem </P>
 * <p> Package: top.parak.examarrangementsystem.annotation </p>
 * <p> FileName: Password <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = PasswordValidator.class
)
public @interface Password {
    String message() default "密码长度非法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
