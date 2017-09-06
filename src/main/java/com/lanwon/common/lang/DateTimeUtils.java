package com.lanwon.common.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期时间工具类
 * 
 * 为了优化程序效率，将所有用过的日期格式缓存到一个Map中，下次使用时无须再次创建DateFormat对象，尽量保证日期格式对象单例。
 * 
 * @author Wangy
 *
 */
public class DateTimeUtils {

    // 日期格式缓存Hash容器
    private static Map<String, DateFormat> formatMap = new HashMap<String, DateFormat>();

    /**
     * 获得某个字符串定义的日期格式对象，优先从Hash容器中获得，如该对象不存在，则创建一个并放入Hash容器中
     * 
     * @param dateFormat
     * @return
     */
    public static DateFormat getFormat(String dateFormat) {
        DateFormat format = formatMap.get(dateFormat);
        if (format == null) {
            format = new SimpleDateFormat(dateFormat);
            formatMap.put(dateFormat, format);
        }
        return format;
    }

    /**
     * 使用某个字符串定义的日期格式对象格式化Date
     * 
     * @param d
     *            需格式化的日期对象
     * @param dateFormat
     *            格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String format(Date d, String dateFormat) {
        return getFormat(dateFormat).format(d);
    }

    /**
     * 使用某个字符串定义的日期格式对象将一段日期字符串解析成Date
     * 
     * @param date
     *            日期字符串
     * @param dateFormat
     *            格式化字符串
     * @return 解析后的日期Date对象
     * @throws ParseException
     *             转换发生错误，抛出异常
     */
    public static Date parse(String date, String dateFormat)
            throws ParseException {
        return getFormat(dateFormat).parse(date);
    }

}
