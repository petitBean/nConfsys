package org.wxz.nconfsyscommon.utils;

import java.util.Random;

/**
 * @Author xingze Wang
 * @create 2020/4/22 12:49
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式，时间加随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random=new Random();
        Integer a=random.nextInt(900000)+100000;//生成六位数
        return System.currentTimeMillis()+String.valueOf(a);
    }
}
