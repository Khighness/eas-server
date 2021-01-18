package top.parak.examarrangementsystem.annotation.impl;

import org.apache.commons.lang3.StringUtils;
import top.parak.examarrangementsystem.annotation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.annotation.impl </p>
 * <p> FileName: EmailValidator <p>
 * <p> Description: 邮箱校验器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

public class EmailValidator implements ConstraintValidator<Email, String> {

    /**
     * <p>初始化</p>
     * @param constraintAnnotation
     */
    public void initialize(Email constraintAnnotation) {

    }

    /**
     * <p>校验规则</p>
     * @param email
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5-\\w]+@[-\\w]+(\\.[-\\w]+)*\\.[a-z]{2,4}$");
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.find();
    }

}
