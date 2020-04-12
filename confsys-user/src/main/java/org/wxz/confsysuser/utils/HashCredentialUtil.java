package org.wxz.confsysuser.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具
 * @Author xingze Wang
 * @create 2020/4/12 20:10
 */
public class HashCredentialUtil {

    /**
     * 加密次数，和realm必须一样
     */
    public final static Integer CODE_TIMES=1;

    /**
     * salt的生成方法
     * @param userName  用户名
     * @return
     */
    public  static String getSalt(String userName){
        return userName+"salt";
    }


    /**
     * 密码加密
     * @param password
     * @param userName
     * @return
     */
    public static String toMD5(String password,String userName){
        String md5Password=new SimpleHash("MD5",password,
                                           ByteSource.Util.bytes(getSalt(userName)),CODE_TIMES).toHex();
        return md5Password;
    }

}
