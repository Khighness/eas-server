package top.parak.examarrangementsystem.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: EncryptUtil <p>
 * <p> Description: 加密工具 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 可以静态调用
 */

public class EncryptUtils {

    /**
     * <p>生成指定位数的字符串，即加密盐值</p>
     * @param n 位数
     * @return 指定位数的随机盐值
     */
    public static String generateSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /**
     * <p>MD5加密: 生成32位密码</p>
     * <p>借助 {@link java.security.MessageDigest} </p>
     * @param password 明文密码
     * @return md5加密后的密码
     */
    public static String encryptByMD5(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * <p>MD5加盐加密</p>
     * @param password 明文密码
     * @param n 盐值位数
     * @return md5加盐加密后的密码
     */
    public static String encryptByMD5AndSalt(String password, int n) {
        String salt = generateSalt(n);
        return encryptByMD5(password + salt);
    }

}
