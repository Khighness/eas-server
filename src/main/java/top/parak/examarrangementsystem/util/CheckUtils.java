package top.parak.examarrangementsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: CheckUtils <p>
 * <p> Description: 检查工具 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 *
 * @apiNote 可以静态调用
 */

public class CheckUtils {

    /**
     * <p>校验邮箱格式</p>
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5-\\w]+@[-\\w]+(\\.[-\\w]+)*\\.[a-z]{2,4}$");
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.find();
    }

    /**
     * <p>校验密码长度</p>
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        return password.length() >= 6 && password.length() <= 16;
    }

    /**
     * <p>校验姓名长度和格式</p>
     * @param name
     * @return
     */
    public static boolean checkName(String name) {
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]+|[A-Za-z]+$");
        Matcher matcher = pattern.matcher(name);
        return name.length() >= 2 && name.length() <= 10 && matcher.find();
    }

    /**
     * <p>校验性别</p>
     * @param gender
     * @return
     */
    public static boolean checkGender(String gender) {
        return gender.equals("男") || gender.equals("女");
    }

    /**
     * <p>校验生日</p>
     * @param date
     * @return
     */
    public static boolean checkBirth(Date date) {
        return date.getTime() / 1000 <= TimeUtils.getSecondTimeStamp();
    }

    /**
     * <p>校验电话号码</p>
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^0\\d{2,3}-\\d{7,9}|1[3|4|5|7|8|9]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

}
