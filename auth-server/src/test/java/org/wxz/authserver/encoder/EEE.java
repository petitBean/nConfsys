package org.wxz.authserver.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author xingze Wang
 * @create 2020/4/16 17:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EEE {

    @Test
    public void t(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String d=encoder.encode("secret");
        System.out.println(d);
    }

}
