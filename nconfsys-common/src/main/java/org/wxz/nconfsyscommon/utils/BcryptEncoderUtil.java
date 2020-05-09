package org.wxz.nconfsyscommon.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author xingze Wang
 * @create 2020/4/22 13:15
 */
public class BcryptEncoderUtil {
    private static BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public static String toBcryptString(String  string){
        return encoder.encode(string);
    }

    public static boolean compare(String encodedPass,String password){
        return encoder.matches(encodedPass,password);
    }
}
