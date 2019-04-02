package com.toni.order.utils;

import java.util.Random;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/27 0027 10:00
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
