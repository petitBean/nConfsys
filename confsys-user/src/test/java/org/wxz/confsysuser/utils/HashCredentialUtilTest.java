package org.wxz.confsysuser.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xingze Wang
 * @create 2020/4/12 20:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class HashCredentialUtilTest {


    @Test
    public  void toMD5(){
        String md5Password=new SimpleHash("MD5","123456",
                ByteSource.Util.bytes("wxzsalt"),1).toHex();
        System.out.println(md5Password);
    }




}