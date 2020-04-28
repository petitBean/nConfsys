package org.wxz.nconfsyscommon.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xingze Wang
 * @create 2020/4/26 16:41
 */
public class DateUtil {

    private final static String FORMAT_STR="yy-MM-dd HH:mm";

    private static DateFormat dateMinuteFormat=new SimpleDateFormat(FORMAT_STR);

    public static Date strToDateMinute(String str) throws Exception{
        return dateMinuteFormat.parse(str);
    }

    public static String dateMinuteToStr(Date date)throws Exception{
        return dateMinuteFormat.format(date);
    }

}
