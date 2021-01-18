package top.parak.examarrangementsystem.annotation.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.annotation.impl </p>
 * <p> FileName: PasswordValidator <p>
 * <p> Description: 密码校验器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

public class PasswordValidator implements ConstraintValidator<Password, String> {

    /**
     * <p>初始化</p>
     * @param constraintAnnotation
     */
    public void initialize(Password constraintAnnotation) {

    }

    /**
     * <p>校验规则：密码长度在6-16位之间</p>
     * @param password
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return (StringUtils.isNotBlank(password) && password.length() >= 6 && password.length() <= 16);
    }
}
