package top.parak.examarrangementsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: TimeUtils <p>
 * <p> Description: 时间工具类<p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/2
 */

public class TimeUtils {

    /**
     * <p>综合时间格式</p>
     */
    public static final String COMPREHENSIVE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * <p>精确时间格式</p>
     */
    public static final String PRECISE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * <p>仅有日期格式</p>
     */
    public static final String ONLY_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * <p>仅有时间格式</p>
     */
    public static final String ONLY_TIME_FORMAT = "HH:mm:ss";


    /**
     * <p>获取当前时间戳</p>
     * @return
     */
    public static int getSecondTimeStamp() {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        return Integer.valueOf(timeStamp);
    }

    /**
     * <p>将指定日期转换成时间戳</p>
     * @param dateStr
     * @return
     */
    public static int transformDateToTimeStamp(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COMPREHENSIVE_FORMAT);
        try {
            String timeStamp = String.valueOf(simpleDateFormat.parse(dateStr).getTime() / 1000);
            return Integer.valueOf(timeStamp);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * <p>将指定时间戳转换成日期</p>
     * @param timeStamp
     * @return
     */
    public static String transformTimeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COMPREHENSIVE_FORMAT);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

}
