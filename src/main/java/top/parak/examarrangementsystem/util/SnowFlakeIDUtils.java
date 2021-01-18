package top.parak.examarrangementsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.util </p>
 * <p> FileName: SnowFlakeIDUtils <p>
 * <p> Description: 雪花ID生成工具类 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/31
 *
 * @apiNote 可以静态调用
 */

public class SnowFlakeIDUtils {

    /**
     * <p>开始时间戳</p>
     */
    private static long START_STMP;
    static {
        String startDateTime = "2001-09-11 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            /* 13位时间戳 */
            START_STMP = simpleDateFormat.parse(startDateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>序列号占用的位数</p>
     */
    private final static long SEQUENCE_BIT = 12;

    /**
     * <p>数据中心标识占用的位数</p>
     */
    private final static long MACHINE_BIT = 5;

    /**
     * <p>机器标识占用的位数</p>
     */
    private final static long DATACENTER_BIT = 5;

    /* 每一部分的最大值：先进行左移运算，再同-1进行异或运算 */

    /**
     * <p>用位运算计算出最大支持的数据中心数量：31</p>
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);

    /**
     * <p>用位运算计算出最大支持的机器数量</p>
     */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);

    /**
     * <p>用位运算计算出最大支持的最大正整数4095</p>
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * <p>机器标志较序列号的偏移量</p>
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * <p>数据中心较机器标志的偏移量</p>
     */
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * <p>时间戳较数据中心的偏移量</p>
     */
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * <p>数据中心</p>
     */
    private static long dataCenterId;

    /**
     * <p>机器标识</p>
     */
    private static long machineId;

    /**
     * <p>序列号</p>
     */
    private static long sequence = 0L;

    /**
     * <p>上一次时间戳</p>
     */
    private static long lastStmp = -1L;

    /**
     * <p>此处无参构造私有，同时没有给出有参构造，在于避免以下两点问题：</p>
     * <li>1. 私有化避免了通过new的方式进行调用，主要是解决了在for循环中通过new的方式调用产生的id不一定唯一问题问题，因为用于记录上一次时间戳的lastStmp永远无法得到比对</li>
     * <li>2. 没有给出有参构造在第一点的基础上考虑了一套分布式系统产生的唯一序列号应该是基于相同的参数</li>
     */
    private SnowFlakeIDUtils() {}

    /**
     * <p>生成ID</p>
     * @return
     */
    public static synchronized long nextID() {
        /* 获取当前时间戳*/
        long currStmp = getNewStmp();
        /* 如果当前时间戳小于上次时间戳则抛出异常 */
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }
        /* 相同毫秒内，序列号自增 */
        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            /* 同一毫秒的序列数已经达到最大*/
            if (sequence == 0L) {
                currStmp = getNextStmp();
            }
        }
        /* 不同毫秒内，序列号设为0 */
        else {
            sequence = 0L;
        }

        /* 当前时间戳存档记录*/
        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT  // 时间戳部分
                | dataCenterId << DATACENTER_LEFT        // 数据中心部分
                | machineId << MACHINE_LEFT              // 机器标识部分
                | sequence;                              // 序列号部分
    }

    /**
     * <p>当前时间戳</p>
     * @return
     */
    public static long getNewStmp() {
        return System.currentTimeMillis();
    }

    /**
     * <p>下一时间的时间戳</p>
     * @return
     */
    public static long getNextStmp() {
        long mill = getNewStmp();
        while (mill <= lastStmp) {
            mill = getNewStmp();
        }
        return mill;
    }

}
