package top.parak.examarrangementsystem.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import top.parak.examarrangementsystem.common.VerifyCodeType;

import java.io.*;
import java.util.Random;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: VerifyCodeUtils <p>
 * <p> Description: 验证码生成工具 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 可以静态调用
 */

public class VerifyCodeUtils {

    private static final String UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private static final String All_NUMBERS = "0123456789";

    /**
     * <p>生成默认类型的验证码</p>
     * @return 6位数字型的字符串验证码
     */
    public static String generateCode() {
        Random random = new Random(System.currentTimeMillis());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int x = random.nextInt(10);
            stringBuffer.append(All_NUMBERS.charAt(x));
        }
        return stringBuffer.toString();
    }

    /**
     * <p>生成指定位数和指定类型的验证码</p>
     * @param n 验证码位数
     * @param VerifyCodeType 验证码类型
     * @return 指定位数和指定类型的字符串验证码
     */
    public static String generateCode(int n, VerifyCodeType VerifyCodeType) {
        Random random = new Random(System.currentTimeMillis());
        StringBuffer stringBuffer = new StringBuffer();
        if (VerifyCodeType == VerifyCodeType.DIGIT) {
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(10);
                stringBuffer.append(All_NUMBERS.charAt(x));
            }
        } else if (VerifyCodeType == VerifyCodeType.LOWER) {
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(26);
                stringBuffer.append(LOWER_LETTERS.charAt(x));
            }
        } else if (VerifyCodeType == VerifyCodeType.UPPER) {
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(26);
                stringBuffer.append(UPPER_LETTERS.charAt(i));
            }
        } else {
            String s = LOWER_LETTERS + UPPER_LETTERS + All_NUMBERS;
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(62);
                stringBuffer.append(s.charAt(x));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * <p>生成默认类型的图片验证码</p>
     * @param outputStream 输出流
     * @return 4位数字和大小写字母混合类型的字符串验证码
     */
    public static String generateImageCode(OutputStream outputStream) {
        RandomGenerator randomGenerator = new RandomGenerator(All_NUMBERS + LOWER_LETTERS + UPPER_LETTERS, 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.write(outputStream);
        return lineCaptcha.getCode();
    }

}
