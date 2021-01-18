package top.parak.examarrangementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.common </p>
 * <p> FileName: HttpStatus <p>
 * <p> Description: Http状态码 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 */

@Getter
@AllArgsConstructor
public enum HttpStatus {

    /*==================================================================
    //                        CustomStatus                             //
    ==================================================================*/

    /**
     * <p>SUCCESS</p>
     */
    SUCCESS(10000, "SUCCESS", "成功"),

    /**
     * <p>CLIENT_ERROR</p>
     */
    CLIENT_ERROR(20000, "CLIENT_ERROR", "客户端错误"),
    REGISTER_ERROR(20100, "REGISTER_ERROR", "注册错误"),
    USERNAME_VERIFICATION_ERROR(20101, "USERNAME_VERIFICATION_ERROR", "用户名校验错误"),
    USERNAME_ALREADY_EXISTS(20102, "USERNAME_ALREADY_EXISTS", "用户名已存在"),
    EMAIL_FORMAT_ERROR(20103, "EMAIL_FORMAT_ERROR", "邮箱格式错误"),
    EMAIL_ALREADY_REGISTERED(20104, "EMAIL_ALREADY_REGISTERED", "邮箱已经注册"),
    PASSWORD_VERIFICATION_ERROR(20105, "PASSWORD_VERIFICATION_ERROR", "密码校验失败"),
    PASSWORD_LENGTH_ERROR(20106, "PASSWORD_LENGTH_ERROR", "密码长度错误"),
    PASSWORD_STRENGTH_LOW(20107, "PASSWORD_STRENGTH_LOW", "密码强度不够"),
    VERIFICATION_CODE_ERROR(20108, "VERIFICATION_CODE_ERROR", "验证码错误"),
    MESSAGE_VERIFICATION_CODE_ERROR(20109, "MESSAGE_VERIFICATION_CODE_ERROR", "短信验证码错误"),
    EMAIL_VERIFICATION_CODE_ERROR(20110, "EMAIL_VERIFICATION_CODE_ERROR", "邮件验证码错误"),
    LOGIN_EXCEPTION(20200, "LOGIN_EXCEPTION", "登录异常"),
    USER_ACCOUNT_NOT_EXISTS(20201, "USER_ACCOUNT_NOT_EXISTS", "用户账户不存在"),
    USER_PASSWORD_ERROR(20202, "USER_PASSWORD_ERROR", "用户密码错误"),
    USER_ACCOUNT_FROZEN(20203, "USER_ACCOUNT_FROZEN", "用户账户被冻结"),

    /**
     * <p>SERVER_ERROR</p>
     */
    SERVER_ERROR(30000, "SERVER_ERROR", "服务器错误");

    private final int code;
    private final String message;
    private final String description;

}
