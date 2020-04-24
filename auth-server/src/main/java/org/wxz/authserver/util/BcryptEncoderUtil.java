package org.wxz.authserver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author xingze Wang
 * @create 2020/4/22 13:27
 */
public class BcryptEncoderUtil {

    public static String toBcryptString(String  string){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(string);
    }

}
