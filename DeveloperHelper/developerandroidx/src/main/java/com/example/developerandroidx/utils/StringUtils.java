package com.example.developerandroidx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @作者： zjf 2020/5/18 10:03
 * @参考：
 * @描述：
 */
public class StringUtils {
    private StringUtils() {
    }

    private static class StringUtilsInstance {
        public static final StringUtils INSTANCE = new StringUtils();
    }

    public static StringUtils getInstance() {
        return StringUtilsInstance.INSTANCE;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getCurrentTime() {

        SimpleDateFormat format = new SimpleDateFormat("yyMMdd HH:mm:ss SSS");
        return format.format(new Date());
    }
}
